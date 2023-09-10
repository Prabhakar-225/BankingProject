package com.banking.loanAccounts.instant.service

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.instant.api.*
import com.banking.loanAccounts.instant.query.InstantLoanRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class InstantLoanService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: InstantLoanRepository) {

    private val logger = LoggerFactory.getLogger(InstantLoanService::class.java)

    fun createInstantLoanAccount(command: CreateInstantLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val accountExist = this.repository.findByAccountNumber(command.accountNumber)
        if (accountExist.isPresent) {
            return CompletableFuture.completedFuture(
                ResponseWithError.ofError("Account Number Already Existed" +
                        " choose Different One")
            )
        }
        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(
                command.consumerName + "  Your HouseLoan Granted with " +
                        " " + command.loanInterest + " Reference Id --> " + command.loanId
            )
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }

    fun updateInstantLoanAccount(command: UpdateInstantLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val existAccount = this.repository.findById(command.loanId!!)
        if (!existAccount.isPresent)
            return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId does not Exist"))

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(
                command.consumerName + " your InstantLoan Account Has been Updated with "  + command.loanId
            )
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }

    fun findAllInstantLoanAccounts(): CompletableFuture<ResponseWithError<List<InstantLoanDto>>>{
        val result = queryGateway.query("findAllInstantLoanAccounts","",
            ResponseTypes.multipleInstancesOf(InstantLoanDto::class.java))

        return result.thenApply { r ->
            if (r.isEmpty()) ResponseWithError.ofError("Accounts Not Present")
            else ResponseWithError.of(r)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }


    }

    fun findInstantLoanAccountByLoanId(loanId: String): CompletableFuture<ResponseWithError<InstantLoanDto>>{
        val result = queryGateway.query("findByLoanId",loanId,
            ResponseTypes.instanceOf(InstantLoanDto::class.java))
        return result.thenApply { r ->
            if (r.deleted == true) ResponseWithError.ofError("Account Deleted")
            else if (r.loanId == null) ResponseWithError.ofError("loanId Should Not Be null")
            else ResponseWithError.of(r)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    fun temporaryDeletion(command: DeleteInstantLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("InstantLoan Deleted Successfully with --> " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

    fun restoreDeletedInstantLoan(command: RestoreDeletedInstantLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("InstantLoan Restored Successfully with --> " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

    fun permanentDeletion(command: PermanentDeleteInstantLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Account Deleted permanently " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

}