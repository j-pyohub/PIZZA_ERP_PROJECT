package com.erp.service;

import com.erp.dto.StoreMenuDTO;
import com.erp.repository.MenuRepository;
import com.erp.repository.StoreMenuRepository;
import com.erp.repository.entity.SalesOrder;
import com.erp.repository.entity.StoreMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
@RequiredArgsConstructor
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;

    public List<StoreMenuDTO> getStoreMenu(Long storeNo) {
        return storeMenuRepository.findStoreMenuByStoreNo(storeNo);
    };


}
