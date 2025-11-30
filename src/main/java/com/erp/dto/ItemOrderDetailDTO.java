package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemOrderDetailDTO {
    private Long itemOrderNo;
    private String itemCode;
    private String itemName;
    private String itemCategoryName;
    private Integer itemOrderQuantity;
    private String supplyUnit;
    private Integer itemPrice;
    private Integer itemOrderPrice; // quantity * price
    private Timestamp receiveDatetime;
}
