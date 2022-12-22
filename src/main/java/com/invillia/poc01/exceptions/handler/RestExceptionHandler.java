package com.invillia.poc01.exceptions.handler;

import com.invillia.poc01.exceptions.MainAddressException;
import com.invillia.poc01.exceptions.MaxLimitException;
import com.invillia.poc01.exceptions.details.MainAddressDetails;
import com.invillia.poc01.exceptions.details.MaxLimitDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MaxLimitException.class)
    public ResponseEntity<?> handleMaxLimitException(MaxLimitException e){
        MaxLimitDetails maxLimitDetails = MaxLimitDetails.MaxLimitDetailsBuilder
                .newBuilder()
                .title("Max Limit Exception")
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(maxLimitDetails, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(MainAddressException.class)
    public ResponseEntity<?> handleMainAddressException(MainAddressException e){
        MainAddressDetails mainAddressDetails = MainAddressDetails.MainAddressDetailsBuilder
                .newBuilder()
                .title("Main Address Exception")
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(mainAddressDetails, HttpStatus.CONFLICT);


    }
}
