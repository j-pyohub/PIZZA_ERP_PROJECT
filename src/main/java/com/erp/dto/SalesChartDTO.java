package com.erp.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class SalesChartDTO {
    private final List<String> labels;   // X축: 날짜(일/주/월/년)
    private final List<Long> values;  // Y축: 매출 합계
}