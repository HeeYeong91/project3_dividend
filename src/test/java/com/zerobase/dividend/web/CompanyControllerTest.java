package com.zerobase.dividend.web;

import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.persist.entity.CompanyEntity;
import com.zerobase.dividend.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CompanyControllerTest {

    @Autowired
    private CompanyService companyService;

    @Test
    @DisplayName("회사 추가 성공")
    void successAddCompany() {
        //given
        String ticker = "MMM";

        //when
        Company company = companyService.save(ticker);
        companyService.addAutocompleteKeyword(company.getName());

        //then
        assertEquals("3M Company", company.getName());
    }
    
    @Test
    @DisplayName("회사 삭제 성공")
    void successDeleteCompany() {
        //given
        String ticker = "MMM";

        Company company = companyService.save(ticker);
        companyService.addAutocompleteKeyword(company.getName());

        //when
        String companyName = companyService.deleteCompany(ticker);

        //then
        assertEquals("3M Company", companyName);
    }
}