package com.banking.fixedDepositAccounts.women.service


import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.women.api.*
import com.banking.fixedDepositAccounts.women.query.WomenFixDepositRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class WomenFixDepositService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: WomenFixDepositRepository
) {

    private val logger = LoggerFactory.getLogger(WomenFixDepositService::class.java)

    // to create account
    fun createWomenFixDepositAccount(command: CreateWomenFixDepositCommand): CompletableFuture<ResponseWithError<String>> {

        val accounts=repository.findByAccountNumber(command.accountNumber)

        if(!accounts.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of(command.accountType + " SuccessFully Created with->" + command.accountId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }else{
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Number already exist choose different one"))
        }
    }

    fun updateWomenFixDepositAccount(command: UpdateWomenFixDepositCommand): CompletableFuture<ResponseWithError<String>> {

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(command.accountType+" SuccessFully Updated with->" + command.accountId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    fun findAllWomenFixDepositAccounts():CompletableFuture<ResponseWithError<List<WomenFixedDepositDto>>>{
        val result = queryGateway.query("findAllFixDepositAccounts", "",
            ResponseTypes.multipleInstancesOf(WomenFixedDepositDto::class.java))
        return result.thenApply { x ->
            if (x.isEmpty()) ResponseWithError.ofError("Accounts Not Available")
            else ResponseWithError.of(x)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }


    fun findWomenFixDepositAccountById(accountId: String): CompletableFuture<ResponseWithError<WomenFixedDepositDto>> {
        val bank = this.repository.findById(accountId)
        if(bank.isPresent) {
            val result = queryGateway.query(
                "findByFixDepositAccountId", accountId,
                ResponseTypes.instanceOf(WomenFixedDepositDto::class.java)
            )

            return result.thenApply { x ->
                if (x.accountId == null) ResponseWithError.ofError("AccountId null found")
                else if (x.deleted == true) ResponseWithError.ofError("AccountId found But Deleted")
                else ResponseWithError.of(x)
            }
                .exceptionally { e -> ResponseWithError.ofError(e.message) }
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Accounts Not Found"))
    }

    fun deleteWomenFixDepositAccount(command: DeleteWomenFixDepositCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.accountId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of(" Deleted Account with ID -> " + command.accountId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun deletePermanentWomenFixDepositAccount(command: DeletePermanentWomenFixDepositCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.accountId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of(" Deleted Account Permanently with ID -> " + command.accountId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun restoreWomenFixDepositAccount(command: RestoreWomenFixDepositCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.accountId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("AccountRestored successfully with ID -> " + command.accountId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }


}