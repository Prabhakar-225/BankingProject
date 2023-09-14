package com.banking.practice.product.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdatedEvent {

    private String productId;
    private  String productName;
    private double productPrice;
    private int productQuantity;
}
