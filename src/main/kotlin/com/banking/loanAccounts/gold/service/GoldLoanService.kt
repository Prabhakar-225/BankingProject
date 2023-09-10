package com.banking.loanAccounts.gold.service

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.forming.api.FormLoanDto
import com.banking.loanAccounts.forming.api.RestoreDeletedFormLoanCommand
import com.banking.loanAccounts.gold.api.*
import com.banking.loanAccounts.gold.query.GoldLoanRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.CompletedEmitterException
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class GoldLoanService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: GoldLoanRepository
) {

    private val logger = LoggerFactory.getLogger(GoldLoanService::class.java)


    fun createAccount(command: CreateGoldLoanAccountCommand): CompletableFuture<ResponseWithError<String>> {
        val accountExist = this.repository.findByAccountNumber(command.accountNumber)
        if (accountExist.isPresent) {
            return CompletableFuture.completedFuture(
                ResponseWithError.ofError("Account Number Already Exist" +
                        " choose Different One")
            )
        }

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(
                "Dear " + command.consumerName + " your LoanAccount Created with " +
                        "Interest " + command.loanInterest + " with this Reference Id " + command.loanId
            )
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    fun updateAccount(command: UpdateGoldLoanAccountCommand): CompletableFuture<ResponseWithError<String>> {
        val accountExist = this.repository.findById(command.loanId!!)
        if (accountExist.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of(
                    "Dear " + command.consumerName + " your LoanAccount has been Updated " +
                            "with Interest " + command.loanInterest + " with Reference Id " + command.loanId
                )
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Found"))
    }

    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<GoldLoanAccountDto>>>{
        val result = queryGateway.query("findAllAccounts","",
            ResponseTypes.multipleInstancesOf(GoldLoanAccountDto::class.java))
        return result.thenApply { r ->
            if(r.isEmpty()) ResponseWithError.ofError("Accounts Are Not Available")
            else ResponseWithError.of(r)
        }.exceptionally { x -> ResponseWithError.ofError(x.message) }
    }

    fun findAccountByLoanId(loanId: String): CompletableFuture<ResponseWithError<GoldLoanAccountDto>>{
        val account = this.repository.findById(loanId)
        if (!account.isPresent){
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Found with " +
                    "this ID "+loanId))
        }
        val result = queryGateway.query("findByLoanId",loanId,
            ResponseTypes.instanceOf(GoldLoanAccountDto::class.java))
        return result.thenApply { x ->
            if(x.loanId ==null) ResponseWithError.ofError("Account Not Available")
            else if (x.deleted == true) ResponseWithError.ofError("Account Fount But Deleted")
            else ResponseWithError.of(x)
        }.exceptionally { r -> ResponseWithError.ofError(r.message) }
    }

    fun deleteAccount(command: DeleteGoldLoanAccountCommand): CompletableFuture<ResponseWithError<String>>{
        val accountDto = this.repository.findById(command.loanId!!)
        if(accountDto.isPresent){
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Account Deleted Successfully with --> "+command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

    fun restoreDeletedAccount(command: RestoreDeletedGoldLoanAccountCommand): CompletableFuture<ResponseWithError<String>>{
        val accountDto = this.repository.findById(command.loanId!!)
        if(accountDto.isPresent){
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Account Restored Successfully with --> "+command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Found"))
    }

    fun accountDeletePermanently(command: PermanentDeleteGoldLoanAccountCommand): CompletableFuture<ResponseWithError<String>>{
        val accountDto = this.repository.findById(command.loanId!!)
        if(accountDto.isPresent){
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Account Deleted Permanently ")
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Found"))
    }

}