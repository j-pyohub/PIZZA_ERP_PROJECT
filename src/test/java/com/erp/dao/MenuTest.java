package com.erp.dao;

import com.erp.dao.dto.MenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MenuTest {
    @Autowired
    private MenuDAO menuDAO;

    @Test
    void addMenuTest() {
        MenuDTO menu = MenuDTO.builder()
                .menuName("띠드 피자")
                .menuCode("C_PIZZA")
                .menuCategory("피자")
                .menuExplain("맛있는 피자입니다")
                .size("단일")
                .menuImage("https://picsum.photos/300/200")
                .menuPrice("25000")
                .releaseStatus("출시 예정")
                .build();
        menuDAO.addMenu(menu);
    }

    @Test
    void removeMenuTest() {
        menuDAO.removeMenu(23L);
    }

}
