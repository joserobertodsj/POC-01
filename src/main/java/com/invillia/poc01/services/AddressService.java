package com.invillia.poc01.services;

import com.invillia.poc01.models.AddressModel;
import com.invillia.poc01.models.dtos.requests.AddressRequestDto;



public interface AddressService {

    AddressModel findById(Long idAddress);

    AddressModel saveAddress (Long IdCustomer, AddressRequestDto addressRequestDto);

    AddressModel updateAddress (Long idAddress, AddressRequestDto addressRequestDto);

    AddressModel updateMainAddress(Long idAddress);

    void delete (Long idAddress);

    void addressLimit(Long IdCustomer);






}
