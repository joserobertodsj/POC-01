package com.invillia.poc01.models.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\d{5}-?\\d{3}$")
    private String zipCode;

    private Boolean mainAddress;

    private Long customerId;

}
