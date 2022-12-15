package com.invillia.poc01.models.dtos.requests;


import com.invillia.poc01.annotations.Document;
import com.invillia.poc01.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")
    private String documentNumber;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String phoneNumber;

}
