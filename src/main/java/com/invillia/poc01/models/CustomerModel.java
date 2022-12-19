package com.invillia.poc01.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invillia.poc01.annotations.Document;
import com.invillia.poc01.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Document
    @NotBlank(message = "Preenchimento obrigatório!")
    private String documentNumber;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AddressModel> addresses =new ArrayList<>();

}
