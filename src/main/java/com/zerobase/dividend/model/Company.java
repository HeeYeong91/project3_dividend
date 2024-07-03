package com.zerobase.dividend.model;

import lombok.*;

/**
 * 회사 모델
 * @author 이희영
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String ticker;

    private String name;
}
