package com.era.api.Exception;


public class LoginException extends RuntimeException{
    String fieldName;
    String message;

    public LoginException(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public LoginException() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginException{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
