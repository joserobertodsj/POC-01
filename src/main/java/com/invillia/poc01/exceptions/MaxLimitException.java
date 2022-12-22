package com.invillia.poc01.exceptions;

import lombok.Getter;


@Getter
public class MaxLimitException extends RuntimeException {
    public MaxLimitException(String message) {
        super(message);
    }
}


