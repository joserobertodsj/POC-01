package com.invillia.poc01.models.dtos.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerResponseUpdateDto {

    private String name;

    private String email;

    private String phoneNumber;
}
