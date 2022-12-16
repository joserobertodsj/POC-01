package com.invillia.poc01.services.impl;

import com.invillia.poc01.exceptions.AddressNotFoundException;
import com.invillia.poc01.exceptions.MainAddressException;
import com.invillia.poc01.exceptions.MaxLimitException;
import com.invillia.poc01.models.AddressModel;
import com.invillia.poc01.models.dtos.requests.AddressRequestDto;
import com.invillia.poc01.repositories.AddressRepository;
import com.invillia.poc01.services.AddressService;
import com.invillia.poc01.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;


    @Override
    public AddressModel findById(Long idAddress) {
        Optional<AddressModel> addressModelOptional = addressRepository.findById(idAddress);
        return addressModelOptional.orElseThrow(()-> new AddressNotFoundException("Endereço não encontrado!"));
    }


    @Override
    @Transactional
    public AddressModel saveAddress(Long idCustomer, AddressRequestDto addressRequestDto) {
        var cutomerModel = customerService.findById(idCustomer);

        if (cutomerModel.getAddresses().isEmpty()){
            addressRequestDto.setMainAddress(true);
        }

        addressLimit(idCustomer);

        var addressModel = new AddressModel();
        BeanUtils.copyProperties(addressRequestDto, addressModel);

        return addressRepository.save(addressModel);
    }

    @Override
    @Transactional
    public AddressModel updateAddress(Long idAddress, AddressRequestDto addressRequestDto) {
        Optional<AddressModel> addressModelOptional = addressRepository.findById(idAddress);
        addressModelOptional.orElseThrow(() -> new AddressNotFoundException("Endereço não encontrado!"));

        var addressModel = addressModelOptional.get();
        BeanUtils.copyProperties(addressRequestDto, addressModel);

        addressModel.setIdAddress(addressModelOptional.get().getIdAddress());

        return addressRepository.save(addressModel);
    }

    @Override
    public AddressModel updateMainAddress(Long idAddress) {
        Optional<AddressModel> addressModelOptional = addressRepository.findById(idAddress);
        addressModelOptional.orElseThrow(() -> new AddressNotFoundException("Endereço não encontrado!"));

        for (AddressModel addresses: addressModelOptional.get().getCustomer().getAddresses()){
            addresses.setMainAddress(false);
            addressRepository.save(addresses);
        }

        var addressModel = addressModelOptional.get();
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
    public void addressLimit(Long idCustomer) {
        var customerModel = customerService.findById(idCustomer);
        if(customerModel.getAddresses().size() >= 5){
            throw new MaxLimitException("Você atingiu o limite de endereços cadastrados!");
        }


    }
}
