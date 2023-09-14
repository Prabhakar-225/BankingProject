package com.banking.practice.product.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDto {
    private String productId;
    private  String productName;
    private double productPrice;
    private int productQuantity;
}
