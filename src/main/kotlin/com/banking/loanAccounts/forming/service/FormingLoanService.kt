package com.banking.loanAccounts.forming.service

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.forming.api.*
import com.banking.loanAccounts.forming.query.FormingLoanRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture


@Component
class FormingLoanService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: FormingLoanRepository) {

    private val logger = LoggerFactory.getLogger(FormingLoanService::class.java)

    //Create an Account
    fun createFormLoan(command: CreateFormLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val existAccount = this.repository.findByAccountNumber(command.accountNumber)
        if(existAccount.isPresent){
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Account existed" +
                    "with another person please choose different"))
        }

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(command.consumerName+" your Loan Account created and granted " +
                    "with loanInterest "+command.loanInterest+" successfully with ID "+command.loanId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    //Update an Account
    fun updateFormLoan(command: UpdateFormLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val accountExist =this.repository.findById(command.loanId!!)
        if(accountExist.isPresent){
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of(command.consumerName+" your Form Loan successfully Updated " +
                        "with ID "+command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Available"))
    }

    //Find All LoanAccounts
    fun findAllLoanAccounts(): CompletableFuture<ResponseWithError<List<FormLoanDto>>>{
        val result = queryGateway.query("findAllLoanAccounts","",
            ResponseTypes.multipleInstancesOf(FormLoanDto::class.java))
        return result.thenApply { r ->
            if(r.isEmpty()) ResponseWithError.ofError("Accounts Are Not Available")
            else ResponseWithError.of(r)
        }.exceptionally { x -> ResponseWithError.ofError(x.message) }
    }

    //find AccountBy loanId
    fun findAccountByLoanId(loanId: String): CompletableFuture<ResponseWithError<FormLoanDto>>{
        val account = this.repository.findById(loanId)
        if (!account.isPresent){
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Account Not Found with " +
                    "this ID "+loanId))
        }
        val result = queryGateway.query("findAccountByLoanId",loanId,
            ResponseTypes.instanceOf(FormLoanDto::class.java))
        return result.thenApply { x ->
            if(x.loanId ==null) ResponseWithError.ofError("Account Not Found")
            else if (x.deleted == true) ResponseWithError.ofError("Account Fount But Deleted")
            else ResponseWithError.of(x)
        }.exceptionally { r -> ResponseWithError.ofError(r.message) }
    }

    //soft Deletion
    fun deleteAccount(command: DeleteFormLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of(" Account Deleted with ID -> " + command.loanId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not found"))
        }
    }

    //Hard Deletion
    fun permanentDeleteAccount(command: PermanentDeleteFormLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of(" Account Deleted Permanently with ID -> " + command.loanId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not found"))
        }
    }

    //Restore Deleted Account
    fun restoreDeletedAccount(command: RestoreDeletedFormLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of(" AccountRestored successfully with ID -> " + command.loanId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not found"))
        }
    }
}