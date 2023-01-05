package com.invillia.poc01.services.impl;

import com.invillia.poc01.exceptions.DocumentNumberException;
import com.invillia.poc01.exceptions.EmailException;
import com.invillia.poc01.exceptions.ModelException;
import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import com.invillia.poc01.models.dtos.requests.CustomerRequestUpdateDto;
import com.invillia.poc01.models.dtos.responses.AddressResponseDto;
import com.invillia.poc01.repositories.CustomerRepository;
import com.invillia.poc01.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerModel findById(Long idCustomer) {
        Optional<CustomerModel> customerModelOptional = customerRepository.findById(idCustomer);
        return customerModelOptional.orElseThrow(ModelException::new);
    }

    @Override
    public Page<CustomerModel> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public CustomerModel saveCustomer(CustomerRequestDto customerRequestDto) {
        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerRequestDto, customerModel);
        existsByDocumentNumber(customerRequestDto.getDocumentNumber());
        existsByEmail(customerRequestDto.getEmail());
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
        customerModelOptional.orElseThrow(ModelException::new);

        existsByEmail(customerRequestUpdateDto.getEmail());

        var customerModel = customerModelOptional.get();
        BeanUtils.copyProperties(customerRequestUpdateDto, customerModel);

        customerModel.setIdCustomer(customerModelOptional.get().getIdCustomer());
        customerModel.setDocumentType(customerModelOptional.get().getDocumentType());
        customerModel.setDocumentNumber(customerModelOptional.get().getDocumentNumber());
        customerModel.setAddresses(customerModelOptional.get().getAddresses());

        return customerRepository.save(customerModel);

    }

    @Override
    public List<AddressResponseDto> getAllAddresses(Long idCustomer) {
        return findById(idCustomer).getAddresses()
                .stream()
                .map(addressModel -> modelMapper.map(addressModel, AddressResponseDto.class))
                .toList();
    }

    @Override
    public void existsByEmail(String email) {
        if (customerRepository.existsByEmail(email)){
            throw new EmailException("E-mail já cadastrado.");
        }
    }


    @Override
    public void existsByDocumentNumber(String documentNumber) {
        if(customerRepository.existsByDocumentNumber(documentNumber)){
            throw new DocumentNumberException("Documento já cadastrado.");
        }
    }

    @Override
    public Page<CustomerModel> findByName(String name, Pageable pageable) {
        Page<CustomerModel> byNameIgnoreCase = customerRepository.findByNameIgnoreCase(name, pageable);
        if (byNameIgnoreCase.isEmpty()){
            throw new ModelException();
        }
        return byNameIgnoreCase;
    }


}
