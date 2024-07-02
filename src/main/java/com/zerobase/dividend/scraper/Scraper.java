package com.zerobase.dividend.scraper;

import com.zerobase.dividend.model.Company;
import com.zerobase.dividend.model.ScrapedResult;

/**
 * 스크래퍼 인터페이스
 * @author 이희영
 */
public interface Scraper {

    /**
     * 티커로 회사 스크랩
     * 
     * @param ticker 티커
     * @return 회사
     */
    Company scrapCompanyByTicker(String ticker);

    /**
     * 회사로 배당 정보 스크랩
     *
     * @param company 회사
     * @return 스크랩 결과
     */
    ScrapedResult scrap(Company company);
}
