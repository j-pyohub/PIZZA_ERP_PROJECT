package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreDailyMenuSalesDTO {

    private String storeName;
    private String menuCategory;
    private String menuName;
    private String size;
    private Long menuCount;
    private Long totalPrice;

}
