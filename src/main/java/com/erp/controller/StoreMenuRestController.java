package com.erp.controller;

import com.erp.dto.StoreMenuDTO;
import com.erp.service.StoreMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storeMenu")
public class StoreMenuRestController {
    private final StoreMenuService storeMenuService;

    @GetMapping("/getStoreMenu/{storeNo}")
    public List<StoreMenuDTO> getStoreMenu(@PathVariable Long storeNo) {
        return storeMenuService.getStoreMenu(storeNo);
    }
}
