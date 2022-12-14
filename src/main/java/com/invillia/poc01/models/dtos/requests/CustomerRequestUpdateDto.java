package com.invillia.poc01.models.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerRequestUpdateDto {

    @NotBlank(message = "Preenchimento obrigatório!")
    private String name;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String phoneNumber;
}
