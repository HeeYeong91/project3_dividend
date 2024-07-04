package com.zerobase.dividend.web;

import com.zerobase.dividend.exception.impl.EmptyTickerException;
import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.model.constants.CacheKey;
import com.zerobase.dividend.persist.entity.CompanyEntity;
import com.zerobase.dividend.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final CacheManager redisCacheManager;

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
    @PreAuthorize("hasRole('READ')")
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
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> addCompany(
            @RequestBody
            Company request
    ) {
        String ticker = request.getTicker().trim();
        if (ObjectUtils.isEmpty(ticker)) {
            throw new EmptyTickerException();
        }

        Company company = this.companyService.save(ticker);
        this.companyService.addAutocompleteKeyword(company.getName());

        return ResponseEntity.ok(company);
    }

    /**
     * 회사 삭제
     *
     * @param ticker 티커
     * @return 응답
     */
    @DeleteMapping("/{ticker}")
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> deleteCompany(
            @PathVariable
            String ticker
    ) {
        String companyName = this.companyService.deleteCompany(ticker);
        this.clearFinanceCache(companyName);

        return ResponseEntity.ok(companyName);
    }

    public void clearFinanceCache(String companyName) {
        this.redisCacheManager.getCache(CacheKey.KEY_FINANCE).evict(companyName);
    }
}
