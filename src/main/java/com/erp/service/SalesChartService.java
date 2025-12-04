package com.erp.service;

import com.erp.dto.MenuRatioDTO;
import com.erp.dto.SalesChartDTO;
import com.erp.dto.StoreDailyMenuSalesDTO;
import com.erp.dto.TotalStoreSalesDTO;
import com.erp.repository.StoreOrderDetailRepository;
import com.erp.repository.StoreSalesRepository;
import com.erp.repository.entity.StoreSales;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesChartService {

    private final StoreSalesRepository storeSalesRepository;
    private final StoreOrderDetailRepository storeOrderDetailRepository;

    public SalesChartDTO getSalesChartByDate(String start, String end, String type, Long storeNo) {
        if (storeNo != null) {
            return getSalesChartByStore(storeNo, start, end, type);
        }
        return getSalesChartByDate(start, end, type);
    }
    public SalesChartDTO getSalesChartByStore(Long storeNo, String start, String end, String type) {

        LocalDate startDate = convert(start, type, false);
        LocalDate endDate   = convert(end, type, true);

        return getStoreSalesChart(storeNo, startDate, endDate, type);
    }

    public SalesChartDTO getStoreSalesChart(Long storeNo, LocalDate startDate, LocalDate endDate, String type) {

        List<StoreSales> list = storeSalesRepository
                .findByStore_StoreNoAndSalesDateBetween(storeNo, startDate, endDate);

        Map<String, Long> grouped;

        switch (type) {
            case "day":
                grouped = groupByDay(list);
                break;
            case "week":
                grouped = groupByWeek(list);
                break;
            case "month":
                grouped = groupByMonth(list);
                break;
            case "year":
                grouped = groupByYear(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }

        List<String> labels = grouped.keySet()
                .stream()
                .sorted()
                .toList();

        List<Long> values = labels.stream()
                .map(grouped::get)
                .toList();

        return SalesChartDTO.builder()
                .labels(labels)
                .values(values)
                .build();
    }



    public List<MenuRatioDTO> getMenuRatio(String startDate,
                                           String endDate,
                                           Long storeNo) {

        LocalDateTime startDt = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime endDt   = LocalDate.parse(endDate).atTime(23, 59, 59);

        List<StoreDailyMenuSalesDTO> list;

        if(storeNo == null){
            list = storeOrderDetailRepository.findDailyMenuSales(startDt, endDt);
        } else {
            list = storeOrderDetailRepository.findDailyMenuSalesByStore(storeNo, startDt, endDt);
        }

        Map<String, Long> grouped = new HashMap<>();

        for (StoreDailyMenuSalesDTO dto : list) {
            grouped.put(dto.getMenuName(),
                    grouped.getOrDefault(dto.getMenuName(), 0L) + dto.getTotalPrice());
        }

        return grouped.entrySet().stream()
                .map(e -> new MenuRatioDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> Long.compare(b.getSalesAmount(), a.getSalesAmount()))
                .toList();
    }

    @Transactional
    public List<TotalStoreSalesDTO> getTotalStoreSales() {

        LocalDate endDate = LocalDate.now().minusDays(1);
        LocalDate startDate = endDate.minusDays(30);

        List<StoreSales> salesList = storeSalesRepository.findBySalesDateBetween(startDate, endDate);

        Map<String, Long> grouped =
                salesList.stream()
                        .collect(Collectors.groupingBy(
                                s -> s.getStore().getStoreName(),
                                Collectors.summingLong(StoreSales::getSalesPrice)
                        ));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(e -> new TotalStoreSalesDTO(e.getKey(), e.getValue()))
                .toList();
    }

    public SalesChartDTO getSalesChart(LocalDate startDate, LocalDate endDate, String type) {

        List<StoreSales> list = storeSalesRepository.findBySalesDateBetween(startDate, endDate);

        Map<String, Long> grouped;

        switch (type) {
            case "day":
                grouped = groupByDay(list);
                break;
            case "week":
                grouped = groupByWeek(list);
                break;
            case "month":
                grouped = groupByMonth(list);
                break;
            case "year":
                grouped = groupByYear(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        List<String> labels = grouped.keySet()
                .stream()
                .sorted()
                .toList();
        List<Long> values = labels.stream()
                .map(grouped::get)
                .toList();

        return SalesChartDTO.builder()
                .labels(labels)
                .values(values)
                .build();
    }

    public SalesChartDTO getSalesChartByDate(String start, String end, String type) {

        LocalDate startDate = convert(start, type, false);
        LocalDate endDate   = convert(end, type, true);

        return getSalesChart(startDate, endDate, type);
    }

    private LocalDate convert(String value, String type, boolean isEnd) {

        switch (type) {

            case "day":
                return LocalDate.parse(value);

            case "week":
                String[] p = value.split("-W");
                int year = Integer.parseInt(p[0]);
                int week = Integer.parseInt(p[1]);

                LocalDate startOfWeek = LocalDate.ofYearDay(year, 1)
                        .with(WeekFields.ISO.weekOfWeekBasedYear(), week)
                        .with(WeekFields.ISO.dayOfWeek(), 1);

                return isEnd ? startOfWeek.plusDays(6) : startOfWeek;

            case "month":
                YearMonth ym = YearMonth.parse(value);
                return isEnd ? ym.atEndOfMonth() : ym.atDay(1);

            case "year":
                int y = Integer.parseInt(value);
                return isEnd ? LocalDate.of(y, 12, 31) : LocalDate.of(y, 1, 1);

            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }




    private Map<String, Long> groupByDay(List<StoreSales> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getSalesDate().toString(),  // yyyy-MM-dd
                        Collectors.summingLong(StoreSales::getSalesPrice)
                ));
    }
    private Map<String, Long> groupByWeek(List<StoreSales> list) {
        WeekFields wf = WeekFields.ISO;
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> {
                            LocalDate d = s.getSalesDate();
                            int year = d.getYear();
                            int week = d.get(wf.weekOfYear());
                            return year + "-W" + week;   // 예: 2025-W7
                        },
                        Collectors.summingLong(StoreSales::getSalesPrice)
                ));
    }

    private Map<String, Long> groupByMonth(List<StoreSales> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> YearMonth.from(s.getSalesDate()).toString(),  // 2025-01
                        Collectors.summingLong(StoreSales::getSalesPrice)
                ));
    }

    private Map<String, Long> groupByYear(List<StoreSales> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        s -> String.valueOf(s.getSalesDate().getYear()),   // 2025
                        Collectors.summingLong(StoreSales::getSalesPrice)
                ));
    }

}


