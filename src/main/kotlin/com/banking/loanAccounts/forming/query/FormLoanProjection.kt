package com.banking.loanAccounts.forming.query

import com.banking.loanAccounts.forming.api.*
import com.banking.loanAccounts.forming.util.FormLoanConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.io.IOException


@Component
class FormLoanProjection(private val repository: FormingLoanRepository) {


    @EventHandler
    fun createFormLoanAccount(event: FormLoanCreatedEvent){

        val state = FormingLoan()
        state.loanId = event.loanId
        state.consumerName = event.consumerName
        state.consumerNumber = event.consumerNumber
        state.consumerAddress = event.consumerAddress
        state.identityDetails = event.identityDetails
        state.landDetails = event.landDetails
        state.bankName = event.bankName
        state.accountNumber = event.accountNumber
        state.ifscCode = event.ifscCode
        state.branchLocation = event.branchLocation
        state.sanctionedAmount = event.sanctionedAmount
        state.loanInterest = event.loanInterest
        state.bankId = event.bankId
        state.deleted = event.deleted

        repository.save(state)
    }

    @EventHandler
    fun updateFormLoanAccount(event: FormLoanUpdatedEvent){
        val account = repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().loanId = event.loanId
            account.get().consumerName = event.consumerName
            account.get().consumerNumber = event.consumerNumber
            account.get().consumerAddress = event.consumerAddress
            account.get().identityDetails = event.identityDetails
            account.get().landDetails = event.landDetails
            account.get().bankName = event.bankName
            account.get().ifscCode = event.ifscCode
            account.get().branchLocation = event.branchLocation
            account.get().sanctionedAmount = event.sanctionedAmount
            account.get().loanInterest = event.loanInterest
            account.get().bankId = event.bankId
            account.get().deleted = event.deleted

            repository.save(account.get())
        }
    }


    @QueryHandler(queryName = "findAllLoanAccounts")
    fun findAllFormLoanAccounts():List<FormLoanDto>{
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { state ->
                FormLoanDto(
                    state.loanId, state.consumerName, state.consumerNumber,
                    state.consumerAddress, state.identityDetails, state.landDetails,
                    state.bankName, state.accountNumber, state.ifscCode, state.branchLocation,
                    state.sanctionedAmount, state.loanInterest, state.bankId, state.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    @QueryHandler(queryName = "findAccountByLoanId")
    fun findFormLoanAccountByLoanId(loanId: String): FormLoanDto{
        val result = repository.findById(loanId)
        if (result.isPresent && result.get().deleted == false) {
            return FormLoanConverter.convertModelToDTO(result.get())
        }
        return FormLoanDto()
    }


    @EventHandler
    fun formLoanAccountDeleteTemporary(event: FormLoanDeletedEvent){
        val account = repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().deleted = true
            repository.save(account.get())
        }
    }

    @EventHandler
    fun formLoanRestoreDeletedAccount(event: DeletedFormLoanRestoredEvent){
        val account = repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().deleted = false
            repository.save(account.get())
        }
    }

    @EventHandler
    fun formLoanAccountDeletePermanently(event: FormLoanPermanentDeletedEvent){
        val account = repository.findById(event.loanId!!)
        if (account.isPresent) {
            repository.delete(account.get())
        }
    }
}