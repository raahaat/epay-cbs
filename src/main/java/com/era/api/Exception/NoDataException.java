package com.era.api.Exception;

public class NoDataException extends RuntimeException{

    String  errorCode;
    String message;

    public NoDataException() {
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public NoDataException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
