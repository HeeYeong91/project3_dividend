package com.zerobase.dividend.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 배당 모델
 * @author 이희영
 */
@Data
@Builder
public class Dividend {

    private LocalDateTime date;

    private String dividend;
}
