package com.zerobase.dividend.exception;

import lombok.Builder;
import lombok.Data;

/**
 * 에러 발생 시 응답 모델 클래스
 * @author 이희영
 */
@Data
@Builder
public class ErrorResponse {

    private int code;

    private String message;
}
