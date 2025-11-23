package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StoreMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long storeMenuNo;
    @ManyToOne
    @JoinColumn(name = "store_no")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "menu_no")
    private Menu menu;
    @Column(nullable = false)
    private String salesStatus;
}
