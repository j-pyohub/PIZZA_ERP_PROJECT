package com.erp.repository;

import com.erp.repository.entity.SalesOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    @Query("""
           SELECT COUNT(o)
           FROM SalesOrder o
           WHERE o.store.storeNo = :storeNo
             AND DATE(o.salesOrderDatetime) = :salesDate
           """)
    int countOrders(@Param("storeNo") Long storeNo, @Param("salesDate") Date salesDate );
}
