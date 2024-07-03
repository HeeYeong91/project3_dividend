package com.zerobase.dividend.web;

import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.persist.entity.CompanyEntity;
import com.zerobase.dividend.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 회사 컨트롤러
 * @author 이희영
 */
@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * 자동 완성
     *
     * @param keyword 키워드
     * @return 응답
     */
    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(
            @RequestParam
            String keyword
    ) {
        var result = this.companyService.autocomplete(keyword);
        return ResponseEntity.ok(result);
    }

    /**
     * 회사 리스트 조회 (페이징 처리)
     *
     * @param pageable 페이저블
     * @return 응답
     */
    @GetMapping
    public ResponseEntity<?> searchCompany(final Pageable pageable) {
        Page<CompanyEntity> companies = this.companyService.getAllCompany(pageable);

        return ResponseEntity.ok(companies);
    }

    /**
     * 회사 및 배당금 정보 추가
     *
     * @param request 회사
     * @return 응답
     */
    @PostMapping
    public ResponseEntity<?> addCompany(
            @RequestBody
            Company request
    ) {
        String ticker = request.getTicker().trim();
        if (ObjectUtils.isEmpty(ticker)) {
            throw new RuntimeException("ticker is empty");
        }

        Company company = this.companyService.save(ticker);
        this.companyService.addAutocompleteKeyword(company.getName());

        return ResponseEntity.ok(company);
    }

    /**
     * 회사 삭제
     *
     * @return 응답
     */
    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
