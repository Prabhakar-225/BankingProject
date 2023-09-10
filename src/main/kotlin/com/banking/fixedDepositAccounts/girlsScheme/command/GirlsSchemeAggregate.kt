package com.banking.fixedDepositAccounts.girlsScheme.command

import com.banking.fixedDepositAccounts.girlsScheme.api.*
import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.fixedDepositAccounts.women.api.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class GirlsSchemeAggregate {

    @AggregateIdentifier
    var girlsSchemeId: String? = null
    var bankName: String? = null
    var accountType: String? = "GirlsBenefit Scheme"
    var schemeName: String? = null
    var accountNumber: Long? = 0L
    var girlName: String? = null
    var depositBalance: Double? = 0.0
    var branchLocation: String? = null
    var ifscCode: String? = null
    var address: Address? = null
    var deleted: Boolean? =false
    var bankId: String? = null

    constructor()


    //Create
    @CommandHandler
    constructor(command: CreateGirlsSchemeCommand){

        AggregateLifecycle.apply(
            GirlsSchemeCreatedEvent(command.girlsSchemeId,command.bankName, command.accountType,
                command.schemeName,command.accountNumber,command.girlName,command.depositBalance,
                command.branchLocation,command.ifscCode,command.address, command.deleted,command.bankId)
        )
    }


    @EventSourcingHandler
    fun handlerForGirlsSchemeAccountCreatedEvent(createdEvent: GirlsSchemeCreatedEvent){
        if(accountNumber!= createdEvent.accountNumber) {
            this.girlsSchemeId = createdEvent.girlsSchemeId
            this.bankName = createdEvent.bankName
            this.accountType = createdEvent.accountType
            this.schemeName = createdEvent.schemeName
            this.accountNumber = createdEvent.accountNumber
            this.girlName = createdEvent.girlName
            this.depositBalance = createdEvent.depositBalance
            this.branchLocation = createdEvent.branchLocation
            this.ifscCode = createdEvent.ifscCode
            this.address = createdEvent.address
            this.deleted = createdEvent.deleted
            this.bankId = createdEvent.bankId
        }
    }

    //Update
    @CommandHandler
    fun handlerForUpdateGirlsSchemeAccountCommand(command: UpdateGirlsSchemeCommand){
        AggregateLifecycle.apply(
            GirlsSchemeUpdatedEvent(command.girlsSchemeId,command.bankName, command.accountType,
                command.schemeName,command.girlName,command.depositBalance,
                command.branchLocation,command.ifscCode,command.address, command.deleted,command.bankId)
        )
    }


    @EventSourcingHandler
    fun handlerForGirlsSchemeAccountUpdatedEvent(updatedEvent: GirlsSchemeUpdatedEvent){
        this.girlsSchemeId = updatedEvent.girlsSchemeId
        this.bankName = updatedEvent.bankName
        this.accountType = updatedEvent.accountType
        this.schemeName = updatedEvent.schemeName
        this.girlName = updatedEvent.girlName
        this.depositBalance = updatedEvent.depositBalance
        this.branchLocation = updatedEvent.branchLocation
        this.ifscCode = updatedEvent.ifscCode
        this.address = updatedEvent.address
        this.deleted = updatedEvent.deleted
        this.bankId = updatedEvent.bankId
    }

    // Soft Deletion
    @CommandHandler
    fun handlerForDeleteGirlsSchemeAccountCommand(command: DeleteGirlsSchemeCommand){
        AggregateLifecycle.apply(GirlsSchemeDeletedEvent(command.girlsSchemeId))
    }

    @EventSourcingHandler
    fun handlerForGirlsSchemeAccountDeletedEvent(deletedEvent: GirlsSchemeDeletedEvent){
        this.deleted=true
    }

    //Permanent Deletion
    @CommandHandler
    fun handlerForDeletePermanent(command: DeleteGirlsSchemePermanentCommand){
        AggregateLifecycle.apply(GirlsSchemePermanentDeletedEvent(command.girlsSchemeId))
    }

    @EventSourcingHandler
    fun handlerForDeletePermanent(event: GirlsSchemePermanentDeletedEvent){

    }

    //restore
    @CommandHandler
    fun handlerForRestore(command: RestoreGirlsSchemeAccountCommand){
        AggregateLifecycle.apply(GirlsSchemeRestoredEvent(command.girlsSchemeId))
    }

    @EventSourcingHandler
    fun handlerForRestoreEvent(restoredEvent: GirlsSchemeRestoredEvent){
        this.deleted=false
    }
}