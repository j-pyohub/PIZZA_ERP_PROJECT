package com.erp.controller;

import com.erp.auth.PrincipalDetails;
import com.erp.controller.request.SearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.dto.StoreItemDTO;
import com.erp.service.StoreItemService;
import com.erp.service.StoreStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreItemRestController {

    private final StoreItemService storeItemService;
    private final StoreStockService storeStockService;

    /** 본사 목록 API → /manager/stock/storeItem/list/{pageNo}  (pageNo: 1-base) */
    @GetMapping("/manager/stock/storeItem/list/{pageNo}")
    public PageResponseDTO<StoreItemDTO> getManagerItems(@PathVariable int pageNo,
                                                         SearchRequestDTO request) {
        request.setPage(Math.max(0, pageNo - 1));
        return storeItemService.getStoreItems(request);
    }

    /** 직영점 목록 API → /store/stock/storeItem/list/{pageNo} (로그인 사용자 storeNo 강제) */
    @PreAuthorize("hasRole('STORE') and principal.store != null")
    @GetMapping("/store/stock/storeItem/list/{pageNo}")
    public PageResponseDTO<StoreItemDTO> getStoreItems(@PathVariable int pageNo,
                                                       SearchRequestDTO request,
                                                       @AuthenticationPrincipal PrincipalDetails p) {
        request.setPage(Math.max(0, pageNo - 1));
        request.setStoreNo(p.getStore().getStoreNo()); // null 체크 제거
        return storeItemService.getStoreItems(request);
    }

    /** 하한선 저장(본사) → /manager/stock/storeItem/{storeItemNo}/limit */
    @PostMapping("/manager/stock/storeItem/{storeItemNo}/limit")
    public void updateLimitByManager(@PathVariable Long storeItemNo,
                                     @RequestParam(required = false) Integer newLimit) {
        storeItemService.setStoreItemLimit(storeItemNo, newLimit, true);
    }

    /** 하한선 저장(직영점) → /store/stock/storeItem/{storeItemNo}/limit */
    @PreAuthorize("hasRole('STORE') and principal.store != null")
    @PostMapping("/store/stock/storeItem/{storeItemNo}/limit")
    public void updateLimitByStore(@PathVariable Long storeItemNo,
                                   @RequestParam(required = false) Integer newLimit) {
        storeItemService.setStoreItemLimit(storeItemNo, newLimit, false);
    }

    /** 폐기 등록(직영점) → /store/stock/storeItem/{storeItemNo}/dispose */
    @PreAuthorize("hasRole('STORE') and principal.store != null")
    @PostMapping("/store/stock/storeItem/{storeItemNo}/dispose")
    public java.util.Map<String, Object> dispose(@PathVariable Long storeItemNo,
                                                 @RequestParam int quantity,
                                                 @RequestParam(required = false) String reason) {
        int current = storeStockService.dispose(storeItemNo, quantity, reason);
        return java.util.Map.of("currentQuantity", current);
    }
}
