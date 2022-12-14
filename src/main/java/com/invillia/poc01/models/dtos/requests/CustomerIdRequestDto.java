package com.invillia.poc01.models.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerIdRequestDto {

    @NotEmpty
    private Long id;
}
