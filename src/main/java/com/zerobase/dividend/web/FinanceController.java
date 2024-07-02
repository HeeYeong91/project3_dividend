package com.zerobase.dividend.web;

import com.zerobase.dividend.service.FinanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 배당 컨트롤러
 * @author 이희영
 */
@RestController
@RequestMapping("/finance")
@AllArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    /**
     * 배당금 조회
     *
     * @param companyName 회사 이름
     * @return 응답
     */
    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<?> searchFinance(
            @PathVariable
            String companyName
    ) {
        var result = this.financeService.getDividendByCompanyName(companyName);
        return ResponseEntity.ok(result);
    }
}
