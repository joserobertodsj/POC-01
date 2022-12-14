package com.invillia.poc01.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @NotEmpty(message = "Preenchimento obrigatório!")
    private Boolean mainAddress;
}
