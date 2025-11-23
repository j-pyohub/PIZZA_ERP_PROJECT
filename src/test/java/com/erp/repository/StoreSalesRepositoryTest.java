package com.erp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;

@SpringBootTest
@Transactional
public class StoreSalesRepositoryTest {
    @Autowired
    private StoreSalesRepository storeSalesRepository;

    @Test
    public void findBySalesDateBetweenTest() {
        System.out.println(storeSalesRepository.findBySalesDateBetween(Date.valueOf("2024-01-05"), Date.valueOf("2024-01-07")));
    }

    @Test
    public void findByStore_StoreNoAndSalesDateBetweenTest() {
        System.out.println(storeSalesRepository.findByStore_StoreNoAndSalesDateBetween(1L, Date.valueOf("2025-12-27"), Date.valueOf("2025-12-30")));
    }
}
