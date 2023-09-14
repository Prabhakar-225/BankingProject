package com.banking.practice.product.util;

import com.banking.practice.product.api.commands.CreateProductCommand;
import com.banking.practice.product.api.dtos.CreateProductDto;

import java.util.UUID;

public  class ProductConverter {

    public static CreateProductCommand convertDtoToCreate(CreateProductDto createDto) {
        return new CreateProductCommand(
                UUID.randomUUID().toString(),
                createDto.getProductName(),
                createDto.getProductPrice(),
                createDto.getProductQuantity());
    }

}
