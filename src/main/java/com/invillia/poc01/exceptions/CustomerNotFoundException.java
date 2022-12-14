package com.invillia.poc01.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message){
        super(message);
    }
}
