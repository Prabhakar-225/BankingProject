package com.banking.practice.product.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStateRepository extends MongoRepository<ProductState,String> {
}
