package com.banking.practice.product.service;


import com.banking.practice.product.api.commands.CreateProductCommand;
import com.banking.practice.product.config.ResponseWithError;
import com.banking.practice.product.query.ProductState;
import com.banking.practice.product.query.ProductStateRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.disruptor.commandhandling.EventPublisher;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class ProductService {

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;
    private ProductStateRepository repository;

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(CommandGateway commandGateway, QueryGateway queryGateway, ProductStateRepository repository) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.repository = repository;
    }

//    public CompletableFuture<ResponseWithError<String>> create(CreateProductCommand command) {
//
//        String result = String.valueOf(commandGateway.send(command).thenApply(r -> {
//            return ResponseWithError.of("Success " + command.getProductId());
//        }).exceptionally(e -> {
//            this.logger.error(e.getMessage());
//            return ResponseWithError.ofError(e.getMessage());
//        }));
//        return result;
//    }

    public String create(CreateProductCommand command) {

        // Send the command to the command gateway.
        commandGateway.send(command);

        // Return a success message.
        return "Success " + command.getProductId();
    }

//    public String create(CreateProductCommand command) {
//
//        // Create a new product.
//
//        EventPublisher eventPublisher = new EventPublisher();
//        // Publish an event to notify other interested components.
//        eventPublisher.publish(new ProductCreatedEvent(product));
//
//        // Return a success message with the product ID.
//        return product != null ? "Success " + product.getId() : "Failed to create product";
//    }


}
