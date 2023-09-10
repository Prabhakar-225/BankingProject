package com.banking.loanAccounts.house.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface HouseLoanRepository extends MongoRepository<HouseLoan, String> {

    @Query("{accountNumber: ?0}")
    Optional<HouseLoan> findByAccountNumber(Long accountNumber);
}
