package com.sparta.scheduleplus.exception.customException;



import com.sparta.scheduleplus.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotValidCookieException extends RuntimeException {
    public final ExceptionCode exceptionCode;

    public NotValidCookieException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
