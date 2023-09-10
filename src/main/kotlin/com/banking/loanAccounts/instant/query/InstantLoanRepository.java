package com.banking.loanAccounts.instant.query;

import com.banking.loanAccounts.house.query.HouseLoan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface InstantLoanRepository extends MongoRepository<InstantLoan, String> {

    @Query("{accountNumber: ?0}")
    Optional<InstantLoan> findByAccountNumber(Long accountNumber);
}
