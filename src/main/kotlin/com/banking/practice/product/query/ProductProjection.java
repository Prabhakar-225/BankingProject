package com.banking.practice.product.query;



import com.banking.practice.product.api.events.ProductCreatedEvent;
import com.banking.practice.product.api.events.ProductUpdatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductProjection {

    @Autowired
    private ProductStateRepository repository;

    @EventHandler
    public void onCreate(ProductCreatedEvent event) {

        ProductState product=new ProductState();

        //BeanUtils.copyProperties(event,product);
        product.setProductId(event.getProductId());
        product.setProductName(event.getProductName());
        product.setProductPrice(event.getProductPrice());
        product.setProductQuantity(event.getProductQuantity());
        repository.save(product);
        //  throw new Exception("Exception Raised: ");
    }

    @EventHandler
    public void onUpdate(ProductUpdatedEvent event){
        Optional<ProductState> product = this.repository.findById(event.getProductId());

        if (product.isPresent()){
            product.get().setProductId(event.getProductId());
            product.get().setProductName(event.getProductName());
            product.get().setProductPrice(event.getProductPrice());
            product.get().setProductQuantity(event.getProductQuantity());
            repository.save(product.get());
        }else
            System.out.println("Product Not Found");
    }


}
