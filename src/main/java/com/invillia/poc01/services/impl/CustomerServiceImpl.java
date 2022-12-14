package com.invillia.poc01.services.impl;

import com.invillia.poc01.exceptions.CustomerNotFoundException;
import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import com.invillia.poc01.models.dtos.requests.CustomerRequestUpdateDto;
import com.invillia.poc01.repositories.CustomerRepository;
import com.invillia.poc01.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerModel findById(Long idCustomer) {

        return customerRepository.findById(idCustomer).orElseThrow(() -> new CustomerNotFoundException("Usuário não encontrado!"));
    }

    @Override
    public List<CustomerModel> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public CustomerModel saveCustomer(CustomerRequestDto customerRequestDto) {
        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerRequestDto, customerModel);
        return customerRepository.save(customerModel);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long idCustomer) {
        CustomerModel customerModel = findById(idCustomer);
        customerRepository.delete(customerModel);
    }

    @Override
    @Transactional
    public CustomerModel updateCustomer(Long idCustomer, CustomerRequestUpdateDto customerRequestUpdateDto) {
        Optional<CustomerModel> customerModelOptional = customerRepository.findById(idCustomer);
        customerModelOptional.orElseThrow(()-> new CustomerNotFoundException("Usuário não encontrado!"));

        var customerModel = customerModelOptional.get();
        BeanUtils.copyProperties(customerRequestUpdateDto, customerModel);

        customerModel.setIdCustomer(customerModelOptional.get().getIdCustomer());
        customerModel.setDocumentType(customerModelOptional.get().getDocumentType());
        customerModel.setDocumentNumber(customerModelOptional.get().getDocumentNumber());
        customerModel.setAddresses(customerModelOptional.get().getAddresses());

        return customerRepository.save(customerModel);

    }
}
