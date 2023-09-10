package com.banking.fixedDepositAccounts.women.query;

import com.banking.fixedDepositAccounts.girlsScheme.query.GirlsScheme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface WomenFixDepositRepository extends MongoRepository<WomenFixDeposit,String> {
    @Query("{accountNumber: ?0}")
    Optional<GirlsScheme> findByAccountNumber(Long accountNumber);
}
