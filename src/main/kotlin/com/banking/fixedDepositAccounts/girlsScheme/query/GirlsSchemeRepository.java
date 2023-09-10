package com.banking.fixedDepositAccounts.girlsScheme.query;

import com.banking.accounts.query.AccountState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface GirlsSchemeRepository extends MongoRepository<GirlsScheme, String> {

    @Query("{accountNumber: ?0}")
    Optional<GirlsScheme> findByAccountNumber(Long accountNumber);
}
