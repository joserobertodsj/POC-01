package com.invillia.poc01.models.dtos.requests;


import com.invillia.poc01.annotations.Document;
import com.invillia.poc01.enums.DocumentType;
import com.invillia.poc01.models.AddressModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {

    @NotBlank(message = "Preenchimento obrigatório!")
    private String name;

    @NotNull(message = "Preenchimento obrigatório!")
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

    @NotNull @Size(min = 1, max = 5, message = "Ao menos 01 endereço deverá ser cadastrado.")
    private List<AddressModel> addresses =new ArrayList<>();


}
