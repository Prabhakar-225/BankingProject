package com.banking.accounts.query;

import com.banking.accounts.query.AccountState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends MongoRepository<AccountState,String> {

    @Query("{balance: { $gt: 20000 }}")
    List<AccountState> findAllAccountsByBalance();

    @Query("{accountNumber: ?0}")
    Optional<AccountState> findByAccountNumber(Long accountNumber);


    @Query("{'accountId': {'$in': ?0}}")
    List<AccountState> findAllByIds(List<String> caravanIds);
//    @Query("{ 'deleted' : true }")
//    long countByDeletedIsTrue();
}
