package com.sparta.scheduleplus.exception.customException;



import com.sparta.scheduleplus.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public DuplicateEmailException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
