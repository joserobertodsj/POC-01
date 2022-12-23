package com.invillia.poc01.controllers;

import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import com.invillia.poc01.models.dtos.requests.CustomerRequestUpdateDto;
import com.invillia.poc01.models.dtos.responses.AddressResponseDto;
import com.invillia.poc01.models.dtos.responses.CustomerResponseDto;
import com.invillia.poc01.models.dtos.responses.CustomerResponseUpdateDto;
import com.invillia.poc01.services.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = ("/customers"))
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    private final ModelMapper modelMapper;


    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getByIdCustomer (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(customerService.findById(id), CustomerResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomer (@PageableDefault(direction = Sort.Direction.ASC, sort = "name")Pageable pageable){
        Page<CustomerModel> customerModelPage = customerService.findAllCustomers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(customerModelPage.map(customerModel -> modelMapper.map(customerModel, CustomerResponseDto.class)));
    }

    @PostMapping
    public  ResponseEntity<CustomerResponseDto> saveCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(customerService.saveCustomer(customerRequestDto), CustomerResponseDto.class));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseUpdateDto> updateCustomer (@PathVariable(value = "id") Long id, @RequestBody @Valid CustomerRequestUpdateDto customerRequestUpdateDto){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(modelMapper.map(customerService.updateCustomer(id, customerRequestUpdateDto), CustomerResponseUpdateDto.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable(value = "id") Long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/addresses/{id}")
    public List<AddressResponseDto> getAllAddresses(@PathVariable(value = "id") Long id){
        return customerService.getAllAddresses(id);
    }


}
