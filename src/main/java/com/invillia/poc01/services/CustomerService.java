package com.invillia.poc01.services;

import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import com.invillia.poc01.models.dtos.requests.CustomerRequestUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService {
    CustomerModel findById(Long idCustomer);

    Page<CustomerModel> findAllCustomers(Pageable pageable);

    CustomerModel saveCustomer(CustomerRequestDto customerRequestDto);

    void deleteCustomer(Long idCustomer);

    CustomerModel updateCustomer(Long idCustomer, CustomerRequestUpdateDto customerRequestUpdateDto);

    
}
