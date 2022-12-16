package com.invillia.poc01.exceptions;

public class MaxLimitException extends RuntimeException {
    public MaxLimitException(String message) {
        super(message);
    }
}
