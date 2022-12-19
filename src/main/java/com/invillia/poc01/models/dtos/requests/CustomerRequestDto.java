package com.invillia.poc01.models.dtos.requests;


import com.invillia.poc01.enums.DocumentType;
import com.invillia.poc01.validation.CustomerSequenceProvider;
import com.invillia.poc01.validation.groupValidation.CnpjGroup;
import com.invillia.poc01.validation.groupValidation.CpfGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;


@Data
@AllArgsConstructor
@NoArgsConstructor
@GroupSequenceProvider(CustomerSequenceProvider.class)
public class CustomerRequestDto {

    @NotBlank(message = "Preenchimento obrigatório!")
    private String name;

    @NotNull(message = "Preenchimento obrigatório!")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @NotBlank(message = "Preenchimento obrigatório!")
    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", message = "Informe um CPF ou CNPJ válido!")
    private String documentNumber;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String phoneNumber;

}
