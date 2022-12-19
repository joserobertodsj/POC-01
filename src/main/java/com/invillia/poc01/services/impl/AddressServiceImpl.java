package com.invillia.poc01.services.impl;

import com.invillia.poc01.exceptions.AddressNotFoundException;
import com.invillia.poc01.exceptions.MainAddressException;
import com.invillia.poc01.exceptions.MaxLimitException;
import com.invillia.poc01.models.AddressModel;
import com.invillia.poc01.models.CustomerModel;
import com.invillia.poc01.models.dtos.requests.AddressRequestDto;
import com.invillia.poc01.models.dtos.requests.AddressRequestPatchDto;
import com.invillia.poc01.repositories.AddressRepository;
import com.invillia.poc01.services.AddressService;
import com.invillia.poc01.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {


    private AddressRepository addressRepository;


    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, CustomerService customerService) {
        this.addressRepository = addressRepository;
        this.customerService = customerService;
    }

    @Override
    public AddressModel findById(Long idAddress) {
        Optional<AddressModel> addressModelOptional = addressRepository.findById(idAddress);
        return addressModelOptional.orElseThrow(()-> new AddressNotFoundException("Endereço não encontrado!"));
    }


    @Override
    @Transactional
    public AddressModel saveAddress(AddressRequestDto addressRequestDto) {

        var customerModel = customerService.findById(addressRequestDto.getCustomerId());

        var addressModel = new AddressModel();
        BeanUtils.copyProperties(addressRequestDto, addressModel);

        addressRequestDto.setMainAddress(customerModel.getAddresses().isEmpty());

        if(customerModel.getAddresses().isEmpty()){
           addressModel.setMainAddress(true);
        }else {
            addressModel.setMainAddress(false);
        }

        addressModel.setCustomer(customerModel);
        addressLimit(customerModel);

        return addressRepository.save(addressModel);

    }

    @Override
    @Transactional
    public AddressModel updateAddress(Long idAddress, AddressRequestPatchDto addressRequestPatchDto) {
        Optional<AddressModel> addressModelOptional = addressRepository.findById(idAddress);
        addressModelOptional.orElseThrow(() -> new AddressNotFoundException("Endereço não encontrado!"));

        var addressModel = addressModelOptional.get();
        BeanUtils.copyProperties(addressRequestPatchDto, addressModel);

        addressModel.setIdAddress(addressModelOptional.get().getIdAddress());
        addressModel.setCustomer(addressModelOptional.get().getCustomer());
        addressModel.setMainAddress(addressModelOptional.get().getMainAddress());

        return addressRepository.save(addressModel);
    }

    @Override
    public AddressModel updateMainAddress(Long idAddress) {
        Optional<AddressModel> addressModelOptional = addressRepository.findById(idAddress);
        addressModelOptional.orElseThrow(() -> new AddressNotFoundException("Endereço não encontrado!"));

        var addressModel = addressModelOptional.get();

        addressModel.getCustomer().getAddresses()
                .forEach(a -> {
                    a.setMainAddress(false);
                    addressRepository.save(a);
        });

        addressModel.setMainAddress(true);

        return addressRepository.save(addressModel);
    }


    @Override
    @Transactional
    public void delete(Long idAddress) {
        AddressModel addressModel = findById(idAddress);
        if (addressModel.getMainAddress().equals(true)){
            throw new MainAddressException("Não é possível deletar o endereço principal.");
        }
        addressRepository.deleteById(idAddress);
    }

    @Override
    public void addressLimit(CustomerModel customerModel) {
        if(customerModel.getAddresses().size() == 5){
            throw new MaxLimitException("Você atingiu o limite de endereços cadastrados!");
        }

    }

}
