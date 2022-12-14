package com.invillia.poc01.models.dtos.responses;

import com.invillia.poc01.enums.DocumentType;
import com.invillia.poc01.models.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long idCustomer;

    private String name;

    private DocumentType documentType;

    private String documentNumber;

    private String email;

    private String phoneNumber;

    private List<AddressResponseDto> addresses;

    public CustomerResponseDto(CustomerModel customer){
        this.idCustomer = customer.getIdCustomer();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.documentType = customer.getDocumentType();
        this.documentNumber = customer.getDocumentNumber();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.addresses = new ArrayList<>();
        this.addresses.addAll(customer.getAddresses().stream().map(AddressResponseDto::new).collect(Collectors.toList()));

    }


}
