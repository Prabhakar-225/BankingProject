package com.banking.accounts.commands

import com.banking.accounts.api.*
import com.banking.accounts.query.CustomerAddress
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class AccountAggregate {


    @AggregateIdentifier
    var accountId: String? = null
    var bankId: String? = null
    var bankName: String? = null
    var bankBranchLocation: String? = null
    var ifscCode: String? = null
    var accountType: String? = null
    var accountNumber: Long? = 0L
    var customerName: String? = null
    var customerAddress: CustomerAddress? = null
    var balance: Double? = null
    var deleted: Boolean? = null


    constructor()


    //Create
    @CommandHandler
    constructor(command: CreateAccountCommand){

        AggregateLifecycle.apply(AccountCreatedEvent(command.accountId,command.bankId,
            command.bankName,command.bankBranchLocation,command.ifscCode,command.accountType,
            command.accountNumber,command.customerName,command.customerAddress,
            command.balance, command.deleted))
    }


    @EventSourcingHandler
    fun handlerForAccountCreatedEvent(createdEvent: AccountCreatedEvent){
        if(accountNumber!= createdEvent.accountNumber) {
            this.accountId = createdEvent.accountId
            this.bankId = createdEvent.bankId
            this.bankName = createdEvent.bankName
            this.bankBranchLocation = createdEvent.bankBranchLocation
            this.ifscCode = createdEvent.ifscCode
            this.accountType = createdEvent.accountType
            this.accountNumber = createdEvent.accountNumber
            this.customerName = createdEvent.customerName
            this.customerAddress = createdEvent.customerAddress
            this.balance = createdEvent.balance
            this.deleted = createdEvent.deleted
        }
    }

    //Update
    @CommandHandler
    fun handlerForUpdateAccountCommand(command: UpdateAccountCommand){
        AggregateLifecycle.apply(AccountUpdatedEvent(command.accountId,command.bankId,
            command.bankName,command.bankBranchLocation,command.ifscCode,command.accountType,
            command.customerName,command.customerAddress,
            command.balance, command.deleted))
    }


    @EventSourcingHandler
    fun handlerForAccountUpdatedEvent(updatedEvent: AccountUpdatedEvent){
        this.accountId = updatedEvent.accountId
        this.bankId = updatedEvent.bankId
        this.bankName = updatedEvent.bankName
        this.bankBranchLocation = updatedEvent.bankBranchLocation
        this.ifscCode = updatedEvent.ifscCode
        this.accountType = updatedEvent.accountType
        this.customerName = updatedEvent.customerName
        this.customerAddress = updatedEvent.customerAddress
        this.balance = updatedEvent.balance
        this.deleted = updatedEvent.deleted
    }

    // Soft Deletion
    @CommandHandler
    fun handlerForDeleteAccountCommand(command: DeleteAccountCommand){
        AggregateLifecycle.apply(AccountDeletedEvent(command.accountId))
    }

    @EventSourcingHandler
    fun handlerForAccountDeletedEvent(deletedEvent: AccountDeletedEvent){
        this.deleted=true
    }

    //Permanent Deletion
    @CommandHandler
    fun handlerForPermanentDeleteAccountCommand(command: DeleteAccountCommand){
        AggregateLifecycle.apply(AccountDeletedEvent(command.accountId))
    }

    @EventSourcingHandler
    fun handlerForPermanentAccountDeletedEvent(deletedEvent: AccountDeletedEvent){
    }

    // Amount DepositOperation
    @CommandHandler
    fun handlerForDepositAmount(command: DepositMoneyCommand){
        AggregateLifecycle.apply(MoneyDepositedEvent(command.accountId,command.amount))

    }

    @EventSourcingHandler
    fun handlerForDepositAmount(event: MoneyDepositedEvent){
        this.balance = (balance?:0.0)+event.amount

    }


    // Amount WithdrawOperation
    @CommandHandler
    fun handlerForWithdrawAmount(command: WithdrawMoneyCommand){
        if((balance?:0.0)<command.amount){
            throw Exception("Insufficient Funds")
        }
        AggregateLifecycle.apply(MoneyWithdrawnEvent(command.accountId,command.amount))
    }

    @EventSourcingHandler
    fun handlerForWithdrawAmount(event: MoneyWithdrawnEvent){

          this.balance= (balance?:0.0) - event.amount

    }

    // Transactions
    @CommandHandler
    fun handlerForTransactions(command: MoneyTransactionCommand){

        if((this.accountId!=command.accountIdSends && this.accountId!=command.accountIdReceived)){
            throw Exception("accountIds are Not Found")
        }
        AggregateLifecycle.apply(MoneyTransferredEvent(command.accountIdSends,command.sendAmount,
            command.accountIdReceived))

    }
    @EventSourcingHandler
    fun handlerForTransactions(event: MoneyTransferredEvent){

    }

}