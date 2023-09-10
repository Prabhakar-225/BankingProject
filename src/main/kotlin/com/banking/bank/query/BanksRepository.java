package com.banking.bank.query;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BanksRepository extends MongoRepository<BankState, String> {

    @Query("{'accountDetails.accountId':  ?0}")
    Optional<BankState> findByAccountId(String accountId);

    @Query("{$and'bankName': ?0}")
    Optional<BankState> findBankName(String bankName);

    @Query("{'accountDetails.accountId': ?0}")
    List<BankState> findAllByIds(List<String> accountIds);
}
