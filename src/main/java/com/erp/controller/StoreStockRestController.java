package com.erp.controller;

import com.erp.auth.PrincipalDetails;
import com.erp.controller.request.SearchRequestDTO;
import com.erp.dto.PageResponseDTO;
import com.erp.dto.StoreStockDTO;
import com.erp.service.StoreStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreStockRestController {

    private final StoreStockService storeStockService;

    /** 본사 변동 목록 → /manager/stock/storeStock/list/{page} */
    @GetMapping("/manager/stock/storeStock/list/{page}")
    public PageResponseDTO<StoreStockDTO> listForManager(@PathVariable int page,
                                                         @ModelAttribute SearchRequestDTO req) {
        req.setPage(page);
        if (req.getSize() == null) req.setSize(10);
        return storeStockService.search(req);
    }

    /** 직영점 변동 목록 → /store/stock/storeStock/list/{page} (로그인 사용자 storeNo 강제) */
    @PreAuthorize("hasRole('STORE') and principal.store != null")
    @GetMapping("/store/stock/storeStock/list/{page}")
    public PageResponseDTO<StoreStockDTO> listForStore(@PathVariable int page,
                                                       @ModelAttribute SearchRequestDTO req,
                                                       @AuthenticationPrincipal PrincipalDetails p) {
        req.setPage(page);
        if (req.getSize() == null) req.setSize(10);
        req.setStoreNo(p.getStore().getStoreNo()); // null 체크 제거
        return storeStockService.search(req);
    }
}
