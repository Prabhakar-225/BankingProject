package com.banking.fixedDepositAccounts.girlsScheme.service

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.girlsScheme.api.*
import com.banking.fixedDepositAccounts.girlsScheme.query.GirlsSchemeRepository
import com.banking.fixedDepositAccounts.women.api.*
import com.banking.fixedDepositAccounts.women.service.WomenFixDepositService
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class GirlsSchemeService(
    private val repository: GirlsSchemeRepository,
    private val queryGateway: QueryGateway,
    private val commandGateway: CommandGateway) {

    private val logger = LoggerFactory.getLogger(GirlsSchemeService::class.java)

    // to create account
    fun createGirlsSchemeAccount(command: CreateGirlsSchemeCommand): CompletableFuture<ResponseWithError<String>> {

        val accounts=repository.findByAccountNumber(command.accountNumber)

        if(!accounts.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of(command.accountType + " Created SuccessFully with->" + command.girlsSchemeId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }else{
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Number already exist choose different one"))
        }
    }

    fun updateGirlsSchemeAccount(command: UpdateGirlsSchemeCommand): CompletableFuture<ResponseWithError<String>> {

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(command.accountType+" Updated SuccessFully with->" + command.girlsSchemeId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    fun findAllGirlsSchemeAccounts(): CompletableFuture<ResponseWithError<List<GirlsSchemeDto>>> {
        val result = queryGateway.query("findAllGirlsSchemeAccounts", "",
            ResponseTypes.multipleInstancesOf(GirlsSchemeDto::class.java))
        return result.thenApply { x ->
            if (x.isEmpty()) ResponseWithError.ofError("Accounts Not Available")
            else ResponseWithError.of(x)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }


    fun findGirlsSchemeById(girlsSchemeId: String): CompletableFuture<ResponseWithError<GirlsSchemeDto>> {
        val bank = this.repository.findById(girlsSchemeId)
        if(bank.isPresent) {
            val result = queryGateway.query(
                "findGirlsSchemeById", girlsSchemeId,
                ResponseTypes.instanceOf(GirlsSchemeDto::class.java)
            )

            return result.thenApply { x ->
                if (x.girlsSchemeId == null) ResponseWithError.ofError("AccountId null found")
                else if (x.deleted == true) ResponseWithError.ofError("AccountId found But Deleted")
                else ResponseWithError.of(x)
            }
                .exceptionally { e -> ResponseWithError.ofError(e.message) }
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Accounts Not Found"))
    }

    fun deleteGirlsSchemeAccount(command: DeleteGirlsSchemeCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.girlsSchemeId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Deleted with ID -> " + command.girlsSchemeId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun deletePermanentGirlsSchemeAccount(command: DeleteGirlsSchemePermanentCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.girlsSchemeId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Deleted with ID -> " + command.girlsSchemeId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun restoreGirlsSchemeAccount(command: RestoreGirlsSchemeAccountCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.girlsSchemeId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Restored successfully with ID -> " + command.girlsSchemeId) }
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