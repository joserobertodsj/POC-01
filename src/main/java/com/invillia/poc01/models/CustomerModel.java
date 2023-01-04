package com.invillia.poc01.models;

import com.invillia.poc01.enums.DocumentType;;
import com.invillia.poc01.validation.groupValidation.CnpjGroup;
import com.invillia.poc01.validation.groupValidation.CpfGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_customer", uniqueConstraints={@UniqueConstraint(columnNames={"documentNumber"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String name;

    @NotNull
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

    @OneToMany(mappedBy = "customer", fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AddressModel> addresses =new ArrayList<>();

}
