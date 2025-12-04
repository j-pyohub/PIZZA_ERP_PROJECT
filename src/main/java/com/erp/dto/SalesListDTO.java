package com.erp.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesListDTO {

    private Long storeNo;
    private String storeName;
    private String address;
    private Long orderCount;
    private Long salesAmount;
    private LocalDate salesDate;
    private String growthRate;

}
