package com.invillia.poc01.validation;

import com.invillia.poc01.models.dtos.requests.CustomerRequestDto;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomerSequenceProvider implements DefaultGroupSequenceProvider<CustomerRequestDto> {

    @Override
    public List<Class<?>> getValidationGroups(CustomerRequestDto customerRequestDto) {

        List<Class<?>> groups = new ArrayList<>();
        groups.add(CustomerRequestDto.class);

        if (isCustomerSelected(customerRequestDto)){
            groups.add(customerRequestDto.getDocumentType().getGroup());
        }
        return groups;

    }

    protected boolean isCustomerSelected(CustomerRequestDto customerRequestDto) {
        return customerRequestDto != null && customerRequestDto.getDocumentType() != null;
    }


}
