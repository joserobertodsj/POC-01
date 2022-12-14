package com.invillia.poc01.models.dtos.responses;

import com.invillia.poc01.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private Long idAddress;

    private String street;

    private String number;

    private String district;

    private String city;

    private String state;

    private String zipCode;

    private Boolean mainAddress;

    public AddressResponseDto(Address address) {
        this.idAddress = address.getIdAddress();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.state = address.getState();
        this.zipCode = address.getZipCode();
        this.mainAddress = address.getMainAddress();
    }
}
