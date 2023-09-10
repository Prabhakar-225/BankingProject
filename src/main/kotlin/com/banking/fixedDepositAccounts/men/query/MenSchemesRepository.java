package com.banking.fixedDepositAccounts.men.query;

import com.banking.fixedDepositAccounts.jointFamily.query.JointFamily;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MenSchemesRepository extends MongoRepository<MenSchemes, String> {

    @Query("{accountNumber: ?0}")
    Optional<MenSchemes> findByAccountNumber(Long accountNumber);
}
