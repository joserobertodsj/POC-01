package com.invillia.poc01.controllers;

import com.invillia.poc01.models.dtos.requests.AddressRequestDto;
import com.invillia.poc01.models.dtos.requests.AddressRequestPatchDto;
import com.invillia.poc01.models.dtos.responses.AddressResponseDto;
import com.invillia.poc01.services.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private final ModelMapper modelMapper;


    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getByIdAddress(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(addressService.findById(id), AddressResponseDto.class));

    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> saveAddress (@RequestBody @Valid AddressRequestDto addressRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(addressService.saveAddress(addressRequestDto), AddressResponseDto.class));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable(value = "id") Long id, @RequestBody @Valid AddressRequestPatchDto addressRequestPatchDto){
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(addressService.updateAddress(id, addressRequestPatchDto), AddressResponseDto.class));
    }

    @PatchMapping("/main-address/{id}")
    public ResponseEntity<AddressResponseDto> updateMainAddress (@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(addressService.updateMainAddress(id), AddressResponseDto.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable(value = "id") Long id){
        addressService.delete(id);

    }



}
