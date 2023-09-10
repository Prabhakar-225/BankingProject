package com.banking.fixedDepositAccounts.jointFamily.service

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.girlsScheme.service.GirlsSchemeService
import com.banking.fixedDepositAccounts.jointFamily.api.*
import com.banking.fixedDepositAccounts.jointFamily.query.JointFamilyRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class JointFamilyService(
    private val repository: JointFamilyRepository,
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    private val logger = LoggerFactory.getLogger(JointFamilyService::class.java)

    fun createJointFamilyScheme(command: CreateJointFamilyCommand): CompletableFuture<ResponseWithError<String>> {
        val existAccountNumber = this.repository.findByAccountNumber(command.accountNumber)

        if (existAccountNumber.isPresent) {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountNumber Already exist please choose different"))
        }

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(command.schemeName + " successfully created with => " + command.jointFamilyId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    fun updateJointFamilyScheme(command: UpdateJointFamilyCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.jointFamilyId!!)

        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("successfully updated with => " + command.jointFamilyId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Can not find " + command.jointFamilyId))
        }
    }


    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<JointFamilyDto>>> {
        val result = queryGateway.query(
            "findAllJointFamilyAccounts", "",
            ResponseTypes.multipleInstancesOf(JointFamilyDto::class.java)
        )
        return result.thenApply { r ->
            if (r.isEmpty()) ResponseWithError.ofError("Accounts are Not Available")
            else ResponseWithError.of(r)
        }.exceptionally { x -> ResponseWithError.ofError(x.message) }
    }


    fun findJointFamilyAccountById(jointFamilyId: String): CompletableFuture<ResponseWithError<JointFamilyDto>> {
        val account = this.repository.findById(jointFamilyId)
        if (account.isPresent) {
            val result = queryGateway.query(
                "findJointFamilyById", jointFamilyId,
                ResponseTypes.instanceOf(JointFamilyDto::class.java)
            )
            return result.thenApply { x ->
                if (x.jointFamilyId == null) ResponseWithError.ofError("AccountId null found")
                else if (x.deleted == true) ResponseWithError.ofError("AccountId found But Deleted")
                else ResponseWithError.of(x)
            }.exceptionally { e -> ResponseWithError.ofError(e.message) }

        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not Found"))
        }
    }

    fun deleteAccount(command: DeleteJointFamilyCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.jointFamilyId!!)

        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Deleted with ID -> " + command.jointFamilyId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun deleteAccountPermanently(command: DeletePermanentJointFamilyCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.jointFamilyId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Permanently Deleted with ID -> " + command.jointFamilyId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("AccountId Not found"))
        }
    }

    fun restoreAccount(command: RestoreJointFamilyCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.jointFamilyId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Account Restored with ID -> " + command.jointFamilyId) }
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