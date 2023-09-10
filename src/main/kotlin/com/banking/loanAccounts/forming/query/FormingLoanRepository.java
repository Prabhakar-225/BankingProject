package com.banking.loanAccounts.forming.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface FormingLoanRepository extends MongoRepository<FormingLoan, String> {

    @Query("{accountNumber: ?0}")
    Optional<FormingLoan> findByAccountNumber(Long accountNumber);
}
