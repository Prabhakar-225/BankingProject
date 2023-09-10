package com.banking.fixedDepositAccounts.women.command

import com.banking.accounts.query.CustomerAddress
import com.banking.fixedDepositAccounts.women.api.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class WomenFixDepositAggregate {

    @AggregateIdentifier
    var accountId: String? = null
    var bankName: String? = null
    var accountType: String? = "WomenFixedDeposit Scheme"
    var accountNumber: Long? = 0L
    var customerName: String? = null
    var depositBalance: Double? = null
    var branchLocation: String? = null
    var ifscCode: String? = null
    var customerAddress: CustomerAddress? = null
    var deleted: Boolean? =false

    constructor()


    //Create
    @CommandHandler
    constructor(command: CreateWomenFixDepositCommand){

        AggregateLifecycle.apply(
            WomenFixDepositCreatedEvent(command.accountId,command.bankName,
            command.accountType,command.accountNumber,command.customerName,command.depositBalance,
            command.branchLocation,command.ifscCode,command.customerAddress, command.deleted)
        )
    }


    @EventSourcingHandler
    fun handlerForWomenFixDepositCreatedEvent(createdEvent: WomenFixDepositCreatedEvent){
        if(accountNumber!= createdEvent.accountNumber) {
            this.accountId = createdEvent.accountId
            this.bankName = createdEvent.bankName
            this.accountType = createdEvent.accountType
            this.accountNumber = createdEvent.accountNumber
            this.customerName = createdEvent.customerName
            this.depositBalance = createdEvent.depositBalance
            this.branchLocation = createdEvent.branchLocation
            this.ifscCode = createdEvent.ifscCode
            this.customerAddress = createdEvent.customerAddress
            this.deleted = createdEvent.deleted
        }
    }

    //Update
    @CommandHandler
    fun handlerForUpdateWomenFixDepositCommand(command: UpdateWomenFixDepositCommand){
        AggregateLifecycle.apply(
            WomenFixDepositUpdatedEvent(command.accountId,command.bankName,
                command.accountType,command.customerName,command.depositBalance,
                command.branchLocation,command.ifscCode,command.customerAddress, command.deleted)
        )
    }


    @EventSourcingHandler
    fun handlerForWomenFixDepositUpdatedEvent(updatedEvent: WomenFixDepositUpdatedEvent){
        this.accountId = updatedEvent.accountId
        this.bankName = updatedEvent.bankName
        this.accountType = updatedEvent.accountType
        this.customerName = updatedEvent.customerName
        this.depositBalance = updatedEvent.depositBalance
        this.branchLocation = updatedEvent.branchLocation
        this.ifscCode = updatedEvent.ifscCode
        this.customerAddress = updatedEvent.customerAddress
        this.deleted = updatedEvent.deleted
    }

    // Soft Deletion
    @CommandHandler
    fun handlerForDeleteWomenFixDepositCommand(command: DeleteWomenFixDepositCommand){
        AggregateLifecycle.apply(WomenFixDepositDeletedEvent(command.accountId))
    }

    @EventSourcingHandler
    fun handlerForWomenFixDepositDeletedEvent(deletedEvent: WomenFixDepositDeletedEvent){
        this.deleted=true
    }

    //permanent Deletion
    @CommandHandler
    fun handlerForPermanentDeletion(command: DeletePermanentWomenFixDepositCommand){
        AggregateLifecycle.apply(WomenFixDepositPermanentDeletedEvent(command.accountId))
    }

    @EventSourcingHandler
    fun handlerForPermanentDeletion(event: WomenFixDepositPermanentDeletedEvent){

    }

    //restore
    @CommandHandler
    fun handlerForRestore(command: RestoreWomenFixDepositCommand){
        AggregateLifecycle.apply(WomenFixDepositRestoredEvent(command.accountId))
    }

    @EventSourcingHandler
    fun handlerForRestoreEvent(restoredEvent: WomenFixDepositRestoredEvent){
        this.deleted = false
    }

}