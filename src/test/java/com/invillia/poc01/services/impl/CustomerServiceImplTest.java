package com.invillia.poc01.services.impl;

import com.invillia.poc01.enums.DocumentType;
import com.invillia.poc01.exceptions.DocumentNumberException;
import com.invillia.poc01.exceptions.EmailException;
import com.invillia.poc01.exceptions.ModelException;
import com.invillia.poc01.models.AddressModel;
import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import com.invillia.poc01.repositories.CustomerRepository;

import jakarta.el.ELException;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    public static final long ID_CUSTOMER = 1L;
    public static final String NAME = "Roberto";
    public static final String DOCUMENT_NUMBER = "639.617.510-05";
    public static final String EMAIL = "jose@gmail.com";
    public static final String PHONE_NUMBER = "83999020588";


    @InjectMocks//cria uma instância real, porem os demais ele mocka
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    private CustomerModel customerModel;

    private CustomerRequestDto customerRequestDto;

    private Optional<CustomerModel> customerModelOptional;

    private Pageable page = Pageable.ofSize(5);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // cria os mocks
        startCustomer();
    }

    @Test
    void whenFindByIdThenReturnAnCustomerInstance() {
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(customerModelOptional); //mockando o findById do repository

        CustomerModel response = customerService.findById(ID_CUSTOMER);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(CustomerModel.class, response.getClass()); //o primeiro argumento é o que estou esperando receber; O segundo argumento é o que realmente o método me retornou
        Assertions.assertEquals(ID_CUSTOMER, response.getIdCustomer());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(DocumentType.CPF, response.getDocumentType());
        Assertions.assertEquals(DOCUMENT_NUMBER, response.getDocumentNumber());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PHONE_NUMBER, response.getPhoneNumber());

    }

    @Test
    void whenFindByIdThenReturnModelException(){
        Mockito.when(customerRepository.findById(Mockito.anyLong())).thenThrow(new ModelException());

        try {
            customerService.findById(ID_CUSTOMER);
        }catch (Exception ex){
            Assertions.assertEquals(ModelException.class, ex.getClass());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfCustomers() {
        List<CustomerModel> customerModelList = Collections.singletonList(new CustomerModel(ID_CUSTOMER,
                NAME,  DocumentType.CPF, DOCUMENT_NUMBER
                , EMAIL, PHONE_NUMBER, new ArrayList<>()));
        Mockito.when(customerRepository.findAll((Pageable)Mockito.any())).thenReturn(new PageImpl<CustomerModel>(customerModelList));

        Page<CustomerModel> response = customerService.findAllCustomers(page);
        Assertions.assertEquals(1, response.getSize());
        Assertions.assertEquals(CustomerModel.class, response.stream().toList().get(0).getClass());
        Assertions.assertEquals(ID_CUSTOMER, response.stream().toList().get(0).getIdCustomer());


    }

    @Test
    void whenCreateThenReturnSuccess() {
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customerModel);

        CustomerModel response = customerService.saveCustomer(customerRequestDto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(CustomerModel.class, response.getClass());
        Assertions.assertEquals(ID_CUSTOMER, response.getIdCustomer());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(DocumentType.CPF, response.getDocumentType());
        Assertions.assertEquals(DOCUMENT_NUMBER, response.getDocumentNumber());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PHONE_NUMBER, response.getPhoneNumber());


    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void getAllAddresses() {
    }

    @Test
    void whenCreateThenReturnAnEmailException() {
        Mockito.when(customerRepository.existsByEmail(Mockito.anyString())).thenThrow(new EmailException("E-mail já cadastrado."));

        try {
            customerService.saveCustomer(customerRequestDto);
        }catch (Exception ex){
            Assertions.assertEquals(EmailException.class, ex.getClass());
            Assertions.assertEquals("E-mail já cadastrado.", ex.getMessage());
        }

    }

    @Test
    void whenCreateThenReturnADocumentNumberException() {
        Mockito.when(customerRepository.existsByDocumentNumber(Mockito.anyString())).thenThrow(new DocumentNumberException("Documento já cadastrado."));

        try {
            customerService.saveCustomer(customerRequestDto);
        }catch (Exception ex){
            Assertions.assertEquals(DocumentNumberException.class, ex.getClass());
            Assertions.assertEquals("Documento já cadastrado.", ex.getMessage());

        }
    }

    @Test
    void findByName() {
    }

    private void startCustomer(){


        customerModel = new CustomerModel(ID_CUSTOMER, NAME,  DocumentType.CPF, DOCUMENT_NUMBER
                , EMAIL, PHONE_NUMBER, new ArrayList<>());

        customerRequestDto = new CustomerRequestDto(NAME,  DocumentType.CPF, DOCUMENT_NUMBER
                , EMAIL, PHONE_NUMBER);

        customerModelOptional = Optional.of(new CustomerModel(ID_CUSTOMER, NAME, DocumentType.CPF,
                DOCUMENT_NUMBER,
                EMAIL, PHONE_NUMBER, new ArrayList<>()));
    }
}