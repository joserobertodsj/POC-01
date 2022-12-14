package com.invillia.poc01.services;

import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import com.invillia.poc01.models.dtos.requests.CustomerRequestUpdateDto;

import java.util.List;

public interface CustomerService {
    CustomerModel findById(Long idCustomer);

    List<CustomerModel> findAllCustomers();

    CustomerModel saveCustomer(CustomerRequestDto customerRequestDto);

    void deleteCustomer(Long idCustomer);

    CustomerModel updateCustomer(Long idCustomer, CustomerRequestUpdateDto customerRequestUpdateDto);

    
}
