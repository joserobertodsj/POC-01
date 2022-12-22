package com.invillia.poc01.services;

import com.invillia.poc01.models.AddressModel;
import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.AddressRequestDto;
import com.invillia.poc01.models.dtos.requests.AddressRequestPatchDto;
import org.springframework.stereotype.Service;


@Service
public interface AddressService {

    AddressModel findById(Long idAddress);

    AddressModel saveAddress (AddressRequestDto addressRequestDto);

    AddressModel updateAddress (Long idAddress, AddressRequestPatchDto addressRequestPatchDto);

    AddressModel updateMainAddress(Long idAddress);

    void delete (Long idAddress);

    void addressLimit(CustomerModel customerModel);




}
