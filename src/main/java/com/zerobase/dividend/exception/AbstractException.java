package com.zerobase.dividend.exception;

/**
 * 추상 예외 클래스
 * @author 이희영
 */
public abstract class AbstractException extends RuntimeException {

    abstract public int getStatusCode();
    abstract public String getMessage();
}
