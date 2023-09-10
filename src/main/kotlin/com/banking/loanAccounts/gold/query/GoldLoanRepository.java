package com.banking.loanAccounts.gold.query;

import com.banking.loanAccounts.forming.query.FormingLoan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface GoldLoanRepository extends MongoRepository<GoldLoan, String> {

    @Query("{accountNumber: ?0}")
    Optional<GoldLoan> findByAccountNumber(Long accountNumber);
}
