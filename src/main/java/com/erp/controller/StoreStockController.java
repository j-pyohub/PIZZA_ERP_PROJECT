package com.erp.controller;

import com.erp.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StoreStockController {

    /** 본사 화면 → /manager/stock/storeStock */
    @GetMapping("/manager/stock/storeStock")
    public String manager() {
        return "stock/storeStockManagerUI";
    }

    /** 직영점 화면 → /store/stock/storeStock */
    @PreAuthorize("hasRole('STORE') and principal.store != null")
    @GetMapping("/store/stock/storeStock")
    public String store(Model model,
                        @AuthenticationPrincipal PrincipalDetails p) {
        model.addAttribute("role", "STORE");
        model.addAttribute("storeNo", p.getStore().getStoreNo()); // null 체크 제거
        return "stock/storeStockStoreUI";
    }
}
