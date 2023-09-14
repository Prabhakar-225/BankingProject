package com.banking.practice.product.controller;

import com.banking.practice.product.api.commands.CreateProductCommand;
import com.banking.practice.product.api.dtos.CreateProductDto;
import com.banking.practice.product.service.ProductService;
import com.banking.practice.product.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/saveProduct")
    public String createProduct(@RequestBody CreateProductDto dto){

        CreateProductCommand productCommand = ProductConverter.convertDtoToCreate(dto);
        return this.service.create(productCommand);
    }

}
