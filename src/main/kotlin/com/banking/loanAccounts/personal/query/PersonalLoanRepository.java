package com.banking.loanAccounts.personal.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PersonalLoanRepository extends MongoRepository<PersonalLoan, String> {
    @Query("{accountNumber: ?0}")
    Optional<PersonalLoan> findByAccountNumber(Long accountNumber);
}
