package com.zerobase.dividend.service;

import com.zerobase.dividend.exception.impl.AlreadyExistTickerException;
import com.zerobase.dividend.exception.impl.IncorrectTickerException;
import com.zerobase.dividend.exception.impl.NoCompanyException;
import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.model.ScrapedResult;
import com.zerobase.dividend.persist.CompanyRepository;
import com.zerobase.dividend.persist.DividendRepository;
import com.zerobase.dividend.persist.entity.CompanyEntity;
import com.zerobase.dividend.persist.entity.DividendEntity;
import com.zerobase.dividend.scraper.Scraper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 회사 서비스
 * @author 이희영
 */
@Service
@AllArgsConstructor
public class CompanyService {

    private final Trie trie;
    private final Scraper yahooFinanceScraper;

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    /**
     * 스크랩된 데이터 저장
     * 
     * @param ticker 티커
     * @return 회사
     */
    public Company save(String ticker) {
        boolean exists = this.companyRepository.existsByTicker(ticker);
        if (exists) {
            throw new AlreadyExistTickerException();
        }

        return this.storeCompanyAndDividend(ticker);
    }

    /**
     * 회사 리스트 조회
     * 
     * @return 회사 페이지
     */
    public Page<CompanyEntity> getAllCompany(Pageable pageable) {
        return this.companyRepository.findAll(pageable);
    }

    /**
     * 회사와 배당 저장
     * 
     * @param ticker 티커
     * @return 회사
     */
    private Company storeCompanyAndDividend(String ticker) {
        // ticker 를 기준으로 회사를 스크랩핑
        Company company = this.yahooFinanceScraper.scrapCompanyByTicker(ticker);
        if (ObjectUtils.isEmpty(company)) {
            throw new IncorrectTickerException();
        }

        // 해당 회사가 존재할 경우, 회사의 배당금 정보 스크래핑
        ScrapedResult scrapedResult = this.yahooFinanceScraper.scrap(company);

        // 스크래핑 결과
        CompanyEntity companyEntity = this.companyRepository.save(new CompanyEntity(company));
        List<DividendEntity> dividendEntities =  scrapedResult.getDividends()
                .stream()
                .map(e -> new DividendEntity(companyEntity.getId(), e))
                .collect(Collectors.toList());
        this.dividendRepository.saveAll(dividendEntities);

        return company;
    }

    /**
     * 자동완성 키워드 추가
     * 
     * @param keyword 키워드
     */
    public void addAutocompleteKeyword(String keyword) {
        this.trie.put(keyword, null);
    }

    /**
     * 자동완성
     * 
     * @param keyword 키워드
     * @return 회사명 리스트
     */
    public List<String> autocomplete(String keyword) {
        return (List<String>) this.trie.prefixMap(keyword).keySet()
                .stream().collect(Collectors.toList());
    }

    /**
     * 키워드 삭제
     * 
     * @param keyword 키워드
     */
    public void deleteAutocompleteKeyword(String keyword) {
        this.trie.remove(keyword);
    }

    /**
     * 회사 삭제
     *
     * @param ticker 티커
     * @return 회사이름
     */
    public String deleteCompany(String ticker) {
        var company = this.companyRepository.findByTicker(ticker)
                .orElseThrow(() -> new NoCompanyException());

        this.dividendRepository.deleteAllByCompanyId(company.getId());
        this.companyRepository.delete(company);
        this.deleteAutocompleteKeyword(company.getName());

        return company.getName();
    }
}
