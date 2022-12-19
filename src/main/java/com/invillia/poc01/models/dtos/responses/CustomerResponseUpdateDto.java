package com.invillia.poc01.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseUpdateDto {

    private String name;

    private String email;

    private String phoneNumber;
}
