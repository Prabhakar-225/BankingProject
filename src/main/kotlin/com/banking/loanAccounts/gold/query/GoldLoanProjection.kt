package com.banking.loanAccounts.gold.query


import com.banking.loanAccounts.gold.api.*
import com.banking.loanAccounts.gold.util.GoldLoanConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class GoldLoanProjection(private val repository: GoldLoanRepository) {

    @EventHandler
    fun saveGoldLoanAccount(event: GoldLoanAccountCreatedEvent) {
        val state = GoldLoan()
        state.loanId = event.loanId
        state.consumerName = event.consumerName
        state.consumerNumber = event.consumerNumber
        state.consumerAddress = event.consumerAddress
        state.identityDetails = event.identityDetails
        state.goldGrams = event.goldGrams
        state.bankName = event.bankName
        state.accountNumber = event.accountNumber
        state.ifscCode = event.ifscCode
        state.branchLocation = event.branchLocation
        state.sanctionedAmount = event.sanctionedAmount
        state.loanInterest = event.loanInterest
        state.bankId = event.bankId
        state.deleted = event.deleted
        this.repository.save(state)
    }

    @EventHandler
    fun updateGoldLoanAccount(event: GoldLoanAccountUpdatedEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().loanId = event.loanId
            account.get().consumerName = event.consumerName
            account.get().consumerNumber = event.consumerNumber
            account.get().consumerAddress = event.consumerAddress
            account.get().identityDetails = event.identityDetails
            account.get().goldGrams = event.goldGrams
            account.get().bankName = event.bankName
            account.get().ifscCode = event.ifscCode
            account.get().branchLocation = event.branchLocation
            account.get().sanctionedAmount = event.sanctionedAmount
            account.get().loanInterest = event.loanInterest
            account.get().bankId = event.bankId
            account.get().deleted = event.deleted
            this.repository.save(account.get())
        }
    }

    @EventHandler
    fun deleteGoldLoanAccount(event: GoldLoanAccountDeletedEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().deleted = true
            this.repository.save(account.get())
        }
    }

    @EventHandler
    fun permanentDeleteGoldLoanAccount(event: GoldLoanAccountPermanentDeletedEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent)
            this.repository.delete(account.get())
    }

    @EventHandler
    fun restoreDeletedGoldLoanAccount(event: DeletedGoldLoanAccountRestoredEvent) {
        val account = this.repository.findById(event.loanId!!)
        if (account.isPresent) {
            account.get().deleted = false
            this.repository.save(account.get())
        }
    }

    @QueryHandler(queryName = "findAllAccounts")
    fun findAll(): List<GoldLoanAccountDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { state ->
                GoldLoanAccountDto(
                    state.loanId, state.consumerName, state.consumerNumber,
                    state.consumerAddress, state.identityDetails, state.goldGrams,
                    state.bankName, state.accountNumber, state.ifscCode,
                    state.branchLocation, state.sanctionedAmount, state.loanInterest,
                    state.bankId, state.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    @QueryHandler(queryName = "findByLoanId")
    fun findByLoanId(loanId: String) : GoldLoanAccountDto{
        val result = repository.findById(loanId)
        if (result.isPresent && result.get().deleted == false) {
            return GoldLoanConverter.convertModelToDTO(result.get())
        }
        return GoldLoanAccountDto()
    }
}