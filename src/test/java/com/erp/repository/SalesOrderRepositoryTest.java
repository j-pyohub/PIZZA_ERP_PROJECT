package com.erp.repository;

import com.erp.repository.entity.SalesOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
public class SalesOrderRepositoryTest {
    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private StoreRepository storeRepository;
    @Test
    public void countOrdersTest() {
        System.out.println(salesOrderRepository.countOrders(1L, Date.valueOf("2025-12-30")));
    }


    @Test
    @Transactional
    public void getSalesOrderTest() {
        var list = salesOrderRepository.findAll();
        list.forEach(System.out::println);
    }

//    @Test
//    @Transactional
//    public void getSalesOrderbyDateTest() {
//        System.out.println(salesOrderRepository.findSalesOrderByDate(LocalDate.parse("2024-01-24")));
//
//    }
}
