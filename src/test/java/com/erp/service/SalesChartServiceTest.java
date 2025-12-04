package com.erp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SalesChartServiceTest {
    @Autowired
    private SalesChartService salesChartService;

//    @Test
//    void getMenuRatio() {
//        System.out.println(salesChartService.getMenuRatio());
//    }

    @Test
    void getTotalStoreSalesTest(){
        System.out.println(salesChartService.getTotalStoreSales());
    }

    @Test
    void getSalesChartTest(){

        LocalDate start = LocalDate.of(2025, 11, 1);
        LocalDate end   = LocalDate.of(2025, 11, 25);
        String type     = "day";

        System.out.println(salesChartService.getSalesChart(start,end, type));

    }
}
