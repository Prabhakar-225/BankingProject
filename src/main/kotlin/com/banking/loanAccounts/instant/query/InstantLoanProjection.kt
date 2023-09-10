package com.banking.loanAccounts.instant.query

import com.banking.loanAccounts.instant.api.*
import com.banking.loanAccounts.instant.util.InstantLoanConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class InstantLoanProjection(private var repository: InstantLoanRepository) {

    @EventHandler
    fun createInstantLoan(event: InstantLoanCreatedEvent) {
        val state = InstantLoan(
            event.loanId, event.consumerName, event.consumerNumber,
            event.personAddress, event.personalDetails, event.bankName, event.accountNumber,
            event.ifscCode, event.branchLocation, event.sanctionedAmount, event.loanInterest,
            event.bankId, event.deleted
        )

        this.repository.save(state)
    }

    @EventHandler
    fun updateInstantLoan(event: InstantLoanUpdatedEvent) {
        val existAccount = this.repository.findById(event.loanId!!)
        if (existAccount.isPresent) {
            existAccount.get().loanId = event.loanId
            existAccount.get().consumerName = event.consumerName
            existAccount.get().consumerNumber = event.consumerNumber
            existAccount.get().personAddress = event.personAddress
            existAccount.get().personalDetails = event.personalDetails
            existAccount.get().bankName = event.bankName
            existAccount.get().ifscCode = event.ifscCode
            existAccount.get().branchLocation = event.branchLocation
            existAccount.get().sanctionedAmount = event.sanctionedAmount
            existAccount.get().loanInterest = event.loanInterest
            existAccount.get().bankId = event.bankId
            existAccount.get().deleted = event.deleted
            this.repository.save(existAccount.get())
        }
    }

    @EventHandler
    fun temporaryDeleteInstantLoan(event: InstantLoanDeletedEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().deleted = true
            this.repository.save(account.get())
        }
    }

    @EventHandler
    fun restoreDeletedInstantLoan(event: DeletedInstantLoanRestoredEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().deleted = false
            this.repository.save(account.get())
        }
    }

    @EventHandler
    fun permanentDeleteInstantLoan(event: InstantLoanPermanentDeletedEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent)
            this.repository.delete(account.get())
    }


    @QueryHandler(queryName = "findAllInstantLoanAccounts")
    fun findAllAccounts(): List<InstantLoanDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { state ->
                InstantLoanDto(
                    state.loanId, state.consumerName, state.consumerNumber,
                    state.personAddress, state.personalDetails, state.bankName, state.accountNumber,
                    state.ifscCode, state.branchLocation, state.sanctionedAmount, state.loanInterest,
                    state.bankId, state.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    @QueryHandler(queryName = "findByLoanId")
    fun findInstantLoanAccount(loanId: String): InstantLoanDto {
        val result = this.repository.findById(loanId)
        if (result.isPresent && result.get().deleted == false) {
            return InstantLoanConverter.convertModelToDto(result.get())
        }else
            return InstantLoanDto()
    }



}