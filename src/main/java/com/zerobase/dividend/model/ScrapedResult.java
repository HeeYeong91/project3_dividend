package com.zerobase.dividend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 스크랩 결과 모델
 * @author 이희영
 */
@Data
@AllArgsConstructor
public class ScrapedResult {

    private Company company;

    private List<Dividend> dividends;

    public ScrapedResult() {
        this.dividends = new ArrayList<>();
    }
}
