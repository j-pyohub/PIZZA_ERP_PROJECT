package com.erp.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderDTO {
    private long salesOrderNo;
    private int salesOrderCount;
    private int salesOrderAmount;

    public SalesOrderDTO(Long salesOrderNo, Long salesOrderCount, Long salesOrderAmount) {
        this.salesOrderNo = salesOrderNo;
        this.salesOrderCount = salesOrderCount.intValue();
        this.salesOrderAmount = salesOrderAmount.intValue();
    }
}
