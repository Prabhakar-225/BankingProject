package com.banking.practice.product.command;
import com.banking.practice.product.api.commands.CreateProductCommand;
import com.banking.practice.product.api.commands.DeleteProductCommand;
import com.banking.practice.product.api.commands.UpdateProductCommand;
import com.banking.practice.product.api.events.ProductCreatedEvent;
import com.banking.practice.product.api.events.ProductDeletedEvent;
import com.banking.practice.product.api.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private  String productName;
    private double productPrice;
    private int productQuantity;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {

        //perform all validation
//        ProductCreatedEvent productCreatedEvent=new ProductCreatedEvent();
//
//        BeanUtils.copyProperties(command,productCreatedEvent);
//
//        AggregateLifecycle.apply(productCreatedEvent);

        AggregateLifecycle.apply(new ProductCreatedEvent(command.getProductId(), command.getProductName(),
                command.getProductPrice(), command.getProductQuantity()));
    }


    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        this.productName= event.getProductName();
        this.productPrice= event.getProductPrice();
        this.productQuantity= event.getProductQuantity();
        this.productId=event.getProductId();
    }

    @CommandHandler
    public void update(UpdateProductCommand command){
        AggregateLifecycle.apply(new ProductUpdatedEvent(command.getProductId(), command.getProductName(),
                command.getProductPrice(), command.getProductQuantity()));
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent event){
        this.productId = event.getProductId();
        this.productName = event.getProductName();
        this.productPrice = event.getProductPrice();
        this.productQuantity = event.getProductQuantity();
    }

    @CommandHandler
    public void delete(DeleteProductCommand command){
        AggregateLifecycle.apply(new ProductDeletedEvent(command.getProductId()));
    }

    @EventSourcingHandler
    public void on(ProductDeletedEvent event){

    }

}
