package com.era.api.Exception;

public class ResourceNotFoundException extends RuntimeException{
    String message;

    public ResourceNotFoundException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
