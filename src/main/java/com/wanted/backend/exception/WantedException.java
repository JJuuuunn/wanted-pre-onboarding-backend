package com.wanted.backend.exception;

import lombok.Getter;

@Getter
public class WantedException  extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public WantedException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
