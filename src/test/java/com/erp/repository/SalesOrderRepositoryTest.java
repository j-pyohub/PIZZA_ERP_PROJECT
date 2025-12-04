package com.erp.repository;

import com.erp.dto.SalesOrderDTO;
import com.erp.repository.entity.SalesOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SalesOrderRepositoryTest {
    @Autowired
    private SalesOrderRepository salesOrderRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    StoreOrderDetailRepository storeOrderDetailRepository;

    @Test
    void findDailyMenuSalesTest(){
        System.out.println(storeOrderDetailRepository.findDailyMenuSales(LocalDateTime.of(2025,11,01,0,0),LocalDateTime.of(2025,12,30,23,59)));

    }

//    @Test
//    void getTotalMenuCountTest(){
//        LocalDate start = LocalDate.of(2025, 12, 2);
//        LocalDate end   = LocalDate.of(2025, 12, 4);
//
//        LocalDateTime startDate = start.atStartOfDay();          // 00:00:00
//        LocalDateTime endDate   = end.atTime(23, 59, 59);
//
//        System.out.println(salesOrderRepository.getTotalMenuCount(startDate, endDate));
//    }
//
//
//    @Test
//    public void countOrdersTest() {
//        System.out.println(salesOrderRepository.countOrders(1L, Date.valueOf("2025-12-30")));
//    }

    @Test
    @Transactional
    void getSalesOrdersTest() {
        List<SalesOrder> orders = salesOrderRepository.findAll();
        List<SalesOrderDTO> dtos = new ArrayList<>();

        for (SalesOrder order : orders) {
            Long orderNo = order.getSalesOrderNo();
            SalesOrderDTO countDto = storeOrderDetailRepository.countSalesOrder(orderNo);
            countDto.setStoreNo(order.getStore().getStoreNo());
            countDto.setStoreName(order.getStore().getStoreName());
            dtos.add(countDto);
        }

        for (SalesOrderDTO dto : dtos) {
            System.out.println(dto);
        }
    }


//    @Test
//    @Transactional
//    void getSalesOrdersByDateTest() {
//
//        LocalDate targetDate = LocalDate.of(2025, 1, 24);
//
//        List<SalesOrder> orders = salesOrderRepository.getSalesOrderbyDate(targetDate);
//        List<SalesOrderDTO> dtos = new ArrayList<>();
//
//        for (SalesOrder order : orders) {
//            Long orderNo = order.getSalesOrderNo();
//            SalesOrderDTO countDto = storeOrderDetailRepository.countSalesOrder(orderNo);
//            countDto.setStoreNo(order.getStore().getStoreNo());
//            countDto.setStoreName(order.getStore().getStoreName());
//            dtos.add(countDto);
//        }
//
//        dtos.forEach(System.out::println);
//    }

//    @Test
//    @Transactional
//    void getSalesOrdersByStoreNoTest() {
//        Long storeNo = 2L;
//
//        List<SalesOrder> orders = salesOrderRepository.getSalesOrdersByStore(storeNo);
//        List<SalesOrderDTO> dtos = new ArrayList<>();
//
//        for (SalesOrder order : orders) {
//            Long orderNo = order.getSalesOrderNo();
//            SalesOrderDTO countDto = storeOrderDetailRepository.countSalesOrder(orderNo);
//            countDto.setStoreNo(order.getStore().getStoreNo());
//            countDto.setStoreName(order.getStore().getStoreName());
//            dtos.add(countDto);
//        }
//
//        for (SalesOrderDTO dto : dtos) {
//            System.out.println(dto);
//        }
//    }

//    @Test
//    @Transactional
//    void getSalesOrdersByStoreAndDateTest() {
//        LocalDate targetDate = LocalDate.of(2025, 12, 30);
//        Long storeNo = 2L;
//
//        List<SalesOrder> orders = salesOrderRepository.getSalesOrderByStoreAndDate(storeNo, targetDate);
//        List<SalesOrderDTO> dtos = new ArrayList<>();
//
//        for (SalesOrder order : orders) {
//            Long orderNo = order.getSalesOrderNo();
//            SalesOrderDTO countDto = storeOrderDetailRepository.countSalesOrder(orderNo);
//            countDto.setStoreNo(order.getStore().getStoreNo());
//            countDto.setStoreName(order.getStore().getStoreName());
//            dtos.add(countDto);
//        }
//
//        for (SalesOrderDTO dto : dtos) {
//            System.out.println(dto);
//        }
//    }

    @Test
    @Transactional
    void getSalesOrderTest(){
        List<SalesOrder> orders = salesOrderRepository.findAll();
        List<SalesOrderDTO> dtos = new ArrayList<>();
        for (SalesOrder order : orders) {
            Long orderNo = order.getSalesOrderNo();
            SalesOrderDTO countDto = storeOrderDetailRepository.countSalesOrder(orderNo);
            countDto.setStoreNo(order.getStore().getStoreNo());
            countDto.setStoreName(order.getStore().getStoreName());
            dtos.add(countDto);
        }
        for (SalesOrderDTO dto : dtos) {
            System.out.println(dto);
        }
    }
}
