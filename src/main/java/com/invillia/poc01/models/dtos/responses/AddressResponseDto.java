package com.invillia.poc01.models.dtos.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private Long idAddress;

    private String street;

    private String number;

    private String district;

    private String city;

    private String state;

    private String zipCode;

    private Boolean mainAddress;
}
