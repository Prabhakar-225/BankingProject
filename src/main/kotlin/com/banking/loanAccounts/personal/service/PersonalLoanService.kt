package com.banking.loanAccounts.personal.service

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.personal.api.*
import com.banking.loanAccounts.personal.query.PersonalLoanRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class PersonalLoanService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val repository: PersonalLoanRepository
) {

    private val logger = LoggerFactory.getLogger(PersonalLoanService::class.java)

    fun createLoan(command: CreatePersonalLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val accountExist = this.repository.findByAccountNumber(command.accountNumber)
        if (accountExist.isPresent) {
            return CompletableFuture.completedFuture(
                ResponseWithError.ofError("Account Number Already Existed " +
                        " choose Different One"))
        }
        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(
                command.personName + " Your HouseLoan Granted with" +
                        " " + command.loanInterest + " Reference Id --> " + command.loanId)
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }


    fun updateLoan(command: UpdatePersonalLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val existAccount = this.repository.findById(command.loanId!!)
        if(!existAccount.isPresent)
            return CompletableFuture.completedFuture(ResponseWithError.ofError("Given LoanId "+
            command.loanId+" Not Present Please Give me Correct One"))

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of(command.personName+" Your personal Loan Updated with Id "+ command.loanId)
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }


    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<PersonalLoanDto>>>{
        val result = queryGateway.query("findAllLoanAccounts","",
            ResponseTypes.multipleInstancesOf(PersonalLoanDto::class.java))

        return result.thenApply { r ->
            if(r.isEmpty()) ResponseWithError.ofError("Accounts are Not Available")
            else ResponseWithError.of(r)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }

    fun findAccountByLoanId(loanId: String): CompletableFuture<ResponseWithError<PersonalLoanDto>>{
        val existAccount = this.repository.findById(loanId)
        if(!existAccount.isPresent)
            return CompletableFuture.completedFuture(ResponseWithError.ofError("No Account Present "))

        val result = queryGateway.query("findPersonalLoanById",loanId,
            ResponseTypes.instanceOf(PersonalLoanDto::class.java))
        return result.thenApply { r ->
            if(r.deleted == true) ResponseWithError.ofError("Account Deleted")
            else if (r.loanId == null) ResponseWithError.ofError("LoanId Not Identified")
            else ResponseWithError.of(r)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }


    fun temporaryDeletion(command: DeletePersonalLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if (account.isPresent) {
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("PersonalLoan Deleted Successfully with --> " + command.loanId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }

    fun restoreAccount(command: RestorePersonalLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if(account.isPresent){
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("PersonalLoan Restored Successfully with -> "+command.loanId)
            }.exceptionally { e ->
                logger.error(e.message)
                ResponseWithError.ofError(e.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found "))
    }


    fun permanentDeletion(command: PermanentDeletePersonalLoanCommand): CompletableFuture<ResponseWithError<String>>{
        val account = this.repository.findById(command.loanId!!)
        if(account.isPresent){
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Loan Account Deleted Permanently with -> "+command.loanId)
            }.exceptionally { e ->
                logger.error(e.message)
                ResponseWithError.ofError(e.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("LoanId Not Found"))
    }



}