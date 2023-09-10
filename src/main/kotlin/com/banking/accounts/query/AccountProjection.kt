package com.banking.accounts.query

import com.banking.accounts.api.*
import com.banking.accounts.util.AccountConvertor
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class AccountProjection(private val repository: AccountRepository) {


    //create Account
    @EventHandler
    fun saveAccount(event: AccountCreatedEvent) {
        val accountState = AccountState()
        accountState.accountId = event.accountId
        accountState.bankId = event .bankId
        accountState.bankName = event.bankName
        accountState.bankBranchLocation = event.bankBranchLocation
        accountState.ifscCode = event.ifscCode
        accountState.accountType = event.accountType
        accountState.accountNumber = event.accountNumber
        accountState.customerName = event.customerName
        accountState.customerAddress = event.customerAddress
        accountState.balance = event.balance
        accountState.deleted = event.deleted

        repository.save(accountState)
    }

    //update Account
    @EventHandler
    fun handlerForUpdateAccountEvent(updatedEvent: AccountUpdatedEvent) {
        val accountState = repository.findById(updatedEvent.accountId!!)
        if (accountState.isPresent) {
            accountState.get().bankId = updatedEvent.bankId
            accountState.get().accountType = updatedEvent.accountType
            accountState.get().bankName = updatedEvent.bankName
            accountState.get().bankBranchLocation = updatedEvent.bankBranchLocation
            accountState.get().ifscCode = updatedEvent.ifscCode
            accountState.get().customerName = updatedEvent.customerName
            accountState.get().customerAddress = updatedEvent.customerAddress
            accountState.get().balance = updatedEvent.balance
            accountState.get().deleted = updatedEvent.deleted

            repository.save(accountState.get())
        }
    }

    // deposit Amount
    @EventHandler
    fun handlerForDepositAmount(event: MoneyDepositedEvent) {

        val account = repository.findById(event.accountId)
        if (account.isPresent) {
            account.get().balance = (account.get().balance ?: 0.0) + event.amount
            repository.save(account.get())
        }
    }

    //withdraw Amount
    @EventHandler
    fun handlerForWithdrawAmount(event: MoneyWithdrawnEvent) {
        val account = repository.findById(event.accountId)
        if (account.isPresent) {
            if ((account.get().balance ?: 0.0) >= event.amount) {
                account.get().balance = (account.get().balance ?: 0.0) - event.amount
                repository.save(account.get())
            }
        }
    }

    // id to id Amount Transactions
    @EventHandler
    fun handlerForTransactions(event: MoneyTransferredEvent) {

    }

    // Soft core deleted Account
    @EventHandler
    fun deleteAccountTemporary(event: AccountDeletedEvent) {
        val account = repository.findById(event.accountId!!)
        if (account.isPresent) {
            account.get().deleted = true
            repository.save(account.get())
        }
    }

    // Permanent Deletion
    @EventHandler
    fun deleteAccountByAccountId(event: AccountDeletedEvent) {
        val state = repository.findById(event.accountId!!)
        if (state.isPresent) {
            repository.delete(state.get())
        }
    }

    //find All Accounts
    @QueryHandler(queryName = "findAllAccounts")
    fun findAllAccounts(): List<AccountDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { x ->
                AccountDto(
                    x.accountId,x.bankId,x.bankName,x.bankBranchLocation,x.ifscCode,
                    x.accountType, x.accountNumber, x.customerName,x.customerAddress,
                    x.balance, x.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    //find All Accounts By Balance
    @QueryHandler(queryName = "findAllRecordsByBalance")
    fun findAllRecordsByBalance(): List<AccountDto> {
        val accounts = this.repository.findAllAccountsByBalance()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { x ->
                AccountDto(
                    x.accountId,x.bankId,x.bankName,x.bankBranchLocation,x.ifscCode,
                    x.accountType, x.accountNumber, x.customerName,x.customerAddress,
                    x.balance, x.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }


    // find Account By accountId
    @QueryHandler(queryName = "findByAccountId")
    fun findByAccountId(accountId: String): AccountDto {
        val result = repository.findById(accountId)
        if (result.isPresent && result.get().deleted == false) {
            return AccountConvertor.convertModelToDTO(result.get())
        }
        return AccountDto()
    }


    //    @QueryHandler(queryName = "deletedRecords")
//    fun findAllDeletedRecords(): Long{
//        var count = this.repository.countByDeletedIsTrue()
//        if(count>0)
//            return count
//        else
//            return 0
//    }


}