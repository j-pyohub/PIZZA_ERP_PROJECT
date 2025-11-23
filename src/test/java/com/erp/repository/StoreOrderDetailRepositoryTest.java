package com.erp.repository;

import com.erp.repository.dto.SalesOrderDTO;
import com.erp.repository.entity.SalesOrder;
import com.erp.repository.entity.Store;
import com.erp.repository.entity.StoreMenu;
import com.erp.repository.entity.StoreOrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StoreOrderDetailRepositoryTest {

    @Autowired
    private StoreOrderDetailRepository storeOrderDetailRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMenuRepository storeMenuRepository;


    @Test
    @Transactional
    @Rollback(false)
    void calculateOrderSummaryTest() {

        Store store = storeRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        SalesOrder order = new SalesOrder();
        order.setStore(store);
        order.setSalesOrderDatetime(LocalDateTime.now());
        order.setSalesOrderAmount(0);
        salesOrderRepository.save(order);

        Long orderNo = order.getSalesOrderNo();

        StoreMenu menu13 = storeMenuRepository.findById(13L)
                .orElseThrow(() -> new RuntimeException("Menu 13 not found"));

        StoreMenu menu14 = storeMenuRepository.findById(14L)
                .orElseThrow(() -> new RuntimeException("Menu 14 not found"));

        StoreOrderDetail detail1 = new StoreOrderDetail();
        detail1.setSalesOrder(order);
        detail1.setStoreMenu(menu13);
        detail1.setMenuCount(2);
        detail1.setMenuPrice(10000);
        storeOrderDetailRepository.save(detail1);

        StoreOrderDetail detail2 = new StoreOrderDetail();
        detail2.setSalesOrder(order);
        detail2.setStoreMenu(menu14);
        detail2.setMenuCount(1);
        detail2.setMenuPrice(8000);
        storeOrderDetailRepository.save(detail2);

        SalesOrderDTO summary = storeOrderDetailRepository.calculateSalesOrder(orderNo);
        int totalAmount = summary.getSalesOrderAmount();
        order.setSalesOrderAmount(totalAmount);
        salesOrderRepository.save(order);

        System.out.println("DB에 저장된 총액 = " + order.getSalesOrderAmount());

    }

}
