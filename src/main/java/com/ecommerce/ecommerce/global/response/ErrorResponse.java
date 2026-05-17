package com.ecommerce.ecommerce.global.response;

import com.ecommerce.ecommerce.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final int status;

    private ErrorResponse(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
            errorCode.name(),
            errorCode.getMessage(),
            errorCode.getStatus().value()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(
            errorCode.name(),
            message,
            errorCode.getStatus().value()
        );
    }
}
