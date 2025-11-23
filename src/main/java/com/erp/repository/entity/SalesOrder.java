package com.erp.repository.entity;

import com.erp.dao.dto.StoreDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
@Table(name = "sales_order")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesOrderNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no")
    private Store store;
    @Column(nullable = false)
    private LocalDateTime salesOrderDatetime;
    @Column(nullable = false)
    private Integer salesOrderAmount;
}
