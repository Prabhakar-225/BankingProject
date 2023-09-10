package com.banking.fixedDepositAccounts.jointFamily.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface JointFamilyRepository extends MongoRepository<JointFamily, String> {

    @Query("{accountNumber: ?0}")
    Optional<JointFamily> findByAccountNumber(Long accountNumber);
}
