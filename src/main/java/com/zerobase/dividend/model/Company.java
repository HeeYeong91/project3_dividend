package com.zerobase.dividend.model;

import lombok.Builder;
import lombok.Data;

/**
 * 회사 모델
 * @author 이희영
 */
@Data
@Builder
public class Company {

    private String ticker;

    private String name;
}
