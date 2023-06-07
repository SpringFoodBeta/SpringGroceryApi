package com.foodapi.food_service.exception;

public class ProductAPIRequestException extends  RuntimeException {
    public ProductAPIRequestException(String message) {
        super(message);
    }

    public ProductAPIRequestException(Throwable cause) {
        super(cause);
    }
}
