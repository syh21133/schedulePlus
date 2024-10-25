package com.sparta.scheduleplus.exception.customException;



import com.sparta.scheduleplus.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotMatchPasswordException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotMatchPasswordException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
