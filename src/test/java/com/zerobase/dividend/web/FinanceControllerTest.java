package com.zerobase.dividend.web;

import com.zerobase.dividend.service.FinanceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FinanceControllerTest {

    @Autowired
    private FinanceService financeService;

    @Test
    @DisplayName("배당금 조회 성공")
    void successSearchFinance() {
        //given
        String companyName = "3M Company";

        //when
        var result = financeService.getDividendByCompanyName(companyName);

        //then
        assertNotNull(result);
        assertEquals("MMM", result.getCompany().getTicker());
    }
}