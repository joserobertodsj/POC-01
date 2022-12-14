package com.invillia.poc01.models.dtos.requests;


import com.invillia.poc01.annotations.Document;
import com.invillia.poc01.enums.DocumentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank(message = "Preenchimento obrigatório!")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Document
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)?(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)", message = "CPF: 0000.000.000-00 / CNPJ: 00.000.000/0000-00")
    private String documentNumber;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String phoneNumber;


}
