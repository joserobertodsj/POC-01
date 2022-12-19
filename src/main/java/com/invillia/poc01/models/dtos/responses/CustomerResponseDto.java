package com.invillia.poc01.models.dtos.responses;

import com.invillia.poc01.enums.DocumentType;
import lombok.*;

import java.util.List;



@Getter
@Setter
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

}
