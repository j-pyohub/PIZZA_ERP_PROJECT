package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TotalStoreSalesDTO {
    private String storeName;
    private long totalSales;
}
