package com.erp.repository;

import com.erp.repository.dto.SalesOrderDTO;
import com.erp.repository.entity.StoreOrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreOrderDetailRepository extends JpaRepository<StoreOrderDetail, Long> {
    @Query("""
    SELECT new com.erp.repository.dto.SalesOrderDTO(
       o.salesOrderNo,
       SUM(d.menuCount),
       SUM(d.menuCount * d.menuPrice)
   )
       FROM StoreOrderDetail d
       JOIN d.salesOrder o
       WHERE o.salesOrderNo = :orderNo
       GROUP BY o.salesOrderNo
""")
    SalesOrderDTO calculateSalesOrder(@Param("orderNo") Long orderNo);

}
