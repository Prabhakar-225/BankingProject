package com.banking.loanAccounts.house.service

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.house.api.*
import com.banking.loanAccounts.house.query.HouseLoanRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class HouseLoanService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: HouseLoanRepository
) {

    private val logger = LoggerFactory.getLogger(HouseLoanService::class.java)

    fun createHouseLoan(command: CreateHouseLoanCommand): CompletableFuture<ResponseWithError<String>> {
        val accountExist = this.repository.findByAccountNumber(command.accountNumber)
        if (accountExist.isPresent) {
            return CompletableFuture.completedFuture(
                ResponseWithError.ofError("Account Number Already Exist" +
                            " choose Different One")
            )
        }
        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(
                command.consumerName + " Your HouseLoan Granted with " +
                        " " + command.loanInterest + " Reference Id --> " + command.loanId
            )
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }

    fun updateHouseLoan(command: UpdateHouseLoanCommand): CompletableFuture<ResponseWithError<String>> {
        val existAccount = this.repository.findById(command.loanId!!)
        if (!existAccount.isPresent)
            return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId does not Exist"))

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(
                command.consumerName + " your HouseLoan Account Has been Updated with " +
                        "--> " + command.loanId
            )
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }

    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<HouseLoanDto>>> {
        val result = queryGateway.query(
            "findAllHouseLoanAccounts", "",
            ResponseTypes.multipleInstancesOf(HouseLoanDto::class.java)
        )

        return result.thenApply { r ->
            if (r.isEmpty()) ResponseWithError.ofError("Accounts Are Not Available")
            else ResponseWithError.of(r)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    fun findByLoanId(loanId: String): CompletableFuture<ResponseWithError<HouseLoanDto>> {
        val account = this.repository.findById(loanId)
        if (!account.isPresent)
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Given LoanId Not Found"))

        val result = queryGateway.query("findDetailsByLoanId", loanId,
            ResponseTypes.instanceOf(HouseLoanDto::class.java)
        )

        return result.thenApply { r ->
            if (r.loanId == null) ResponseWithError.ofError("Account Not Available")
            else if (r.deleted == true) ResponseWithError.ofError("Account Found But Deleted")
            else ResponseWithError.of(r)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    fun temporaryDeletion(command: DeleteHouseLoanCommand): CompletableFuture<ResponseWithError<String>> {
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("AccountDeleted Successfully with --> " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

    fun restoreDeletedAccount(command: RestoreDeletedHouseLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("AccountRestored Successfully with --> " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

    fun permanentDeletion(command: PermanentDeleteHouseLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Account Deleted Permanently Successfully with --> " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }
}