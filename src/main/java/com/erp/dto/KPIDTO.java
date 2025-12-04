package com.erp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KPIDTO {
    private long  totalSales;
    private long  totalMenuCount;
    private long  avgStoreSales;
    private long  avgOrderAmount;
    private Double growthRate;
}