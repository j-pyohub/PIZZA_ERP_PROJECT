package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeOrderDetailNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_no")
    private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_menu_no")
    private StoreMenu storeMenu;

    @Column(nullable = false)
    private int menuCount;
    @Column(nullable = false)
    private int menuPrice;
}
