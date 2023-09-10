package com.banking.accounts.service

import com.banking.accounts.api.*
import com.banking.accounts.query.AccountRepository
import com.banking.configurations.ResponseWithError
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class AccountService(private val commandGateway: CommandGateway,
                    private val queryGateway: QueryGateway,
                    private val repository: AccountRepository) {

    private val logger = LoggerFactory.getLogger(AccountService::class.java)

    // to create account
    fun createAccount(command: CreateAccountCommand): CompletableFuture<ResponseEntity<String>> {
        val accounts = repository.findByAccountNumber(command.accountNumber)

        if (accounts.isPresent) {
            return CompletableFuture.completedFuture(
                ResponseEntity(
                    "Account Number already exist choose different one",
                    HttpStatus.BAD_REQUEST
                )
            )
        }

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseEntity<String>("Account Created successfully...@ with -> " + command.accountId, HttpStatus.CREATED)
        }.exceptionally { ResponseEntity<String>("Account Not Created...", HttpStatus.BAD_REQUEST) }
        return result
    }

    // to update account
    fun updateAccount(command: UpdateAccountCommand): CompletableFuture<ResponseEntity<String>> {
        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseEntity<String>("Account updated successfully...@ with ->" + command.accountId, HttpStatus.CREATED)
        }.exceptionally { ResponseEntity<String>("Account Not updated...", HttpStatus.INTERNAL_SERVER_ERROR) }

        return result
    }

    // to deposit amount
    fun depositAmount(command: DepositMoneyCommand): CompletableFuture<ResponseWithError<String>> {
//        val account= repository.findById(command.accountId)
//        account.get().balance =(account.get().balance?:0.0)+command.amount
        val result = commandGateway.send<Any>(command).thenApply { d ->
            ResponseWithError.of("Amount Deposited..")
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.of(x.message)
        }
        return result
    }

    // withdraw operation
    fun withdrawBalance(command: WithdrawMoneyCommand): CompletableFuture<ResponseWithError<String>> {
//        val account=repository.findById(command.accountId)
//        if(account.isPresent) {
//            if ((account.get().balance ?: 0.0) >= command.amount) {
//                account.get().balance = (account.get().balance ?: 0.0) - command.amount
//                repository.save(account.get())

        val result = commandGateway.send<Any>(command).thenApply { w ->
            ResponseWithError.of("Amount withdraw successful")
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.of(e.message)
        }
        return result
//            }else return CompletableFuture.completedFuture(ResponseWithError.ofError("Insufficient fund Try again"))
//        }else return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Found"))
    }

    fun amountTransactions(command: MoneyTransactionCommand): CompletableFuture<ResponseWithError<String>> {
        val sendingAccount = repository.findById(command.accountIdSends)
        val receivingAccount = repository.findById(command.accountIdReceived)
        if (sendingAccount.isPresent && receivingAccount.isPresent) {

            if ((sendingAccount.get().balance ?: 0.0) >= command.sendAmount) {
                sendingAccount.get().balance = (sendingAccount.get().balance ?: 0.0) - command.sendAmount
                receivingAccount.get().balance = (receivingAccount.get().balance ?: 0.0) + command.sendAmount
                repository.save(sendingAccount.get())
                repository.save(receivingAccount.get())

                val result = commandGateway.send<Any>(command).thenApply { w ->
                    ResponseWithError.of("Transaction successfully Completed")
                }.exceptionally { e ->
                    logger.error(e.message)
                    ResponseWithError.of(e.message)
                }
                return result
            } else return CompletableFuture.completedFuture(ResponseWithError.ofError("Insufficient fund Try again"))
        } else return CompletableFuture.completedFuture(ResponseWithError.ofError("accountId Entered wrong try again"))
    }

    //to find all records which is balance gt 20000
    fun findAllRecordsByBalance(): CompletableFuture<ResponseWithError<List<AccountDto>>> {
        val result = queryGateway.query(
            "findAllRecordsByBalance", "", ResponseTypes.multipleInstancesOf(AccountDto::class.java)
        )
        return result.thenApply { all ->
            if (all.isEmpty()) ResponseWithError.ofError("Records Not Found")
            else ResponseWithError.of(all)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    // to find all records by balance
    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<AccountDto>>> {
        val result = queryGateway.query(
            "findAllAccounts", "",
            ResponseTypes.multipleInstancesOf(AccountDto::class.java)
        )
        return result.thenApply { x ->
            if (x.isEmpty()) ResponseWithError.ofError("Account Not Found")
            else ResponseWithError.of(x)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }


//    fun findDeletedRecords(): CompletableFuture<ResponseWithError<List<AccountDto>>>{
//        val result = queryGateway.query("deletedRecords", "",
//            ResponseTypes.multipleInstancesOf(AccountDto::class.java))
//        return result.thenApply { x ->
//            if (x.isEmpty()) ResponseWithError.ofError("Account Not Found")
//            else ResponseWithError.of(x)
//        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
//    }


    // to find a record by accountId
    fun findByAccountId(accountId: String): CompletableFuture<ResponseWithError<AccountDto>> {
        val result = queryGateway.query("findByAccountId", accountId, ResponseTypes.instanceOf(AccountDto::class.java))

        return result.thenApply { x ->
            if (x.accountId == null) ResponseWithError.ofError("accountId null found")
            else if (x.deleted == true) ResponseWithError.ofError("accountId found But Deleted")
            else ResponseWithError.of(x)
        }
            .exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    // to delete a record temporary present in database
    fun deleteAccountByIdTemporary(command: DeleteAccountCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.accountId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Delete with ID -> " + command.accountId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    // to delete a record permanently loss in database
    fun deleteAccountByIdPermanent(command: DeleteAccountCommand): CompletableFuture<ResponseWithError<String>> {
        val result = commandGateway.send<Any>(command).thenApply { d ->
            ResponseWithError.of("Account Deleted permanently with -> ")
        }.exceptionally { r ->
            logger.error(r.message)
            ResponseWithError.ofError(r.message)
        }
        return result
    }
}