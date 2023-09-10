package com.banking.fixedDepositAccounts.women.query

import com.banking.fixedDepositAccounts.women.api.*
import com.banking.fixedDepositAccounts.women.util.WomenFixDepositConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class WomenFixDepositProjection(private val repository: WomenFixDepositRepository) {

    //create Account
    @EventHandler
    fun saveAccount(event: WomenFixDepositCreatedEvent) {
        val state = WomenFixDeposit()
        state.accountId = event.accountId
        state.bankName = event.bankName
        state.accountType = event.accountType
        state.accountNumber = event.accountNumber
        state.customerName = event.customerName
        state.depositBalance = event.depositBalance
        state.branchLocation = event.branchLocation
        state.ifscCode = event.ifscCode
        state.customerAddress = event.customerAddress
        state.deleted = event.deleted

        repository.save(state)
    }

    //update Account
    @EventHandler
    fun handlerForUpdateAccountEvent(updatedEvent: WomenFixDepositUpdatedEvent) {
        val account = repository.findById(updatedEvent.accountId!!)
        if (account.isPresent) {
            account.get().bankName = updatedEvent.bankName
            account.get().accountType = updatedEvent.accountType
            account.get().customerName = updatedEvent.customerName
            account.get().depositBalance = updatedEvent.depositBalance
            account.get().branchLocation = updatedEvent.branchLocation
            account.get().ifscCode = updatedEvent.ifscCode
            account.get().customerAddress = updatedEvent.customerAddress
            account.get().deleted = updatedEvent.deleted

            repository.save(account.get())
        }
    }

    // Soft core deleted Account
    @EventHandler
    fun deleteWomenFixDepositAccountTemporary(event: WomenFixDepositDeletedEvent) {
        val account = repository.findById(event.accountId!!)
        if (account.isPresent) {
            account.get().deleted = true
            repository.save(account.get())
        }
    }

    // Hard core deleted Account
    @EventHandler
    fun deleteWomenFixDepositAccountPermanently(event: WomenFixDepositPermanentDeletedEvent) {
        val account = repository.findById(event.accountId!!)
        if (account.isPresent) {
            repository.delete(account.get())
        }
    }

    //Restore
    @EventHandler
    fun restoreWomenFixDepositAccount(event: WomenFixDepositRestoredEvent){
        val account = repository.findById(event.accountId!!)
        if(account.isPresent){
            account.get().deleted = false
            this.repository.save(account.get())
        }
    }

    //find All Accounts
    @QueryHandler(queryName = "findAllFixDepositAccounts")
    fun findAllWomenFixDepositAccounts(): List<WomenFixedDepositDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { x ->
                WomenFixedDepositDto(
                    x.accountId,x.bankName,x.accountType,x.accountNumber,x.customerName,
                    x.depositBalance, x.branchLocation, x.ifscCode,x.customerAddress,
                    x.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    // find Account By accountId
    @QueryHandler(queryName = "findByFixDepositAccountId")
    fun findWomenFixDepositAccountById(accountId: String): WomenFixedDepositDto {
        val result = repository.findById(accountId)
        if (result.isPresent && result.get().deleted == false) {
            return WomenFixDepositConverter.convertModelToDTO(result.get())
        }
        return WomenFixedDepositDto()
    }

}