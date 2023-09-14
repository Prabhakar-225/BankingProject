package com.banking.practice.product.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private  String productName;
    private double productPrice;
    private int productQuantity;

}
