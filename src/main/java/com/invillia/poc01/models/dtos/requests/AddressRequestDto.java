package com.invillia.poc01.models.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

    @NotBlank(message = "Preenchimento obrigatório!")
    private String street;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String number;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String district;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String city;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String state;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String zipCode;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private Boolean mainAddress;



}
