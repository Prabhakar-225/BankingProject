package com.banking.fixedDepositAccounts.men.service

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.jointFamily.api.DeleteJointFamilyCommand
import com.banking.fixedDepositAccounts.men.api.*
import com.banking.fixedDepositAccounts.men.query.MenSchemesRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class MenSchemeService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: MenSchemesRepository
) {


    private val logger = LoggerFactory.getLogger(MenSchemeService::class.java)

    fun createAccount(command: CreateMenSchemeCommand): CompletableFuture<ResponseWithError<String>> {
        val existAccount = this.repository.findByAccountNumber(command.accountNumber)
        if (existAccount.isPresent) {
            return CompletableFuture.completedFuture(
                ResponseWithError.ofError("Account Already exist choose different AccountNumber")
            )
        }

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(command.schemeName + " successfully Created with -> " + command.menSchemeId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    fun updateAccount(command: UpdateMenSchemeCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.menSchemeId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of(command.schemeName + " successfully Updated with -> " + command.menSchemeId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Available"))
        }
    }

    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<MenSchemeDto>>> {

        val result = queryGateway.query(
            "findAllMenSchemeAccounts", "",
            ResponseTypes.multipleInstancesOf(MenSchemeDto::class.java)
        )
        return result.thenApply { r ->
            if (r.isEmpty()) ResponseWithError.ofError("Accounts are Not Available")
            else ResponseWithError.of(r)
        }.exceptionally { x -> ResponseWithError.ofError(x.message) }
    }

    fun findAccountById(menSchemeId: String): CompletableFuture<ResponseWithError<MenSchemeDto>> {
        val account = this.repository.findById(menSchemeId)
        if (account.isPresent) {
            val result = queryGateway.query(
                "findAccountById", menSchemeId,
                ResponseTypes.instanceOf(MenSchemeDto::class.java)
            )
            return result.thenApply { x ->
                if (x.menSchemeId == null) ResponseWithError.ofError("AccountId null found")
                else if (x.deleted == true) ResponseWithError.ofError("AccountId found But Deleted")
                else ResponseWithError.of(x)
            }.exceptionally { e -> ResponseWithError.ofError(e.message) }
        } else {
            return CompletableFuture.completedFuture(
                ResponseWithError.ofError("Account Not Available")
            )
        }
    }

    fun deleteAccount(command: DeleteMenSchemeCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.menSchemeId!!)

        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Deleted with ID -> " + command.menSchemeId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun permanentDeleteAccount(command: PermanentDeleteMenSchemeCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.menSchemeId!!)

        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Deleted permanently with ID -> " + command.menSchemeId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun restoreDeletedAccount(command: RestoreDeletedMenSchemeCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.menSchemeId!!)

        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("AccountRestored with ID -> " + command.menSchemeId) }
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