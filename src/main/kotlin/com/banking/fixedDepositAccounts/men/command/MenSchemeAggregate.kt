package com.banking.fixedDepositAccounts.men.command

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.fixedDepositAccounts.men.api.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class MenSchemeAggregate {

    @AggregateIdentifier
    var menSchemeId: String? = null
    var schemeName: String? = "Men's Benefit Scheme"
    var purposeOfScheme: String? = null
    var accountNumber: Long? = null
    var nameOfPerson: String? = null
    var phoneNumber: Long? = null
    var address: Address? = null
    var bankName: String? = null
    var branchLocation: String? = null
    var ifscCode: String? = null
    var depositBalance: Double? = 0.0
    var bankId: String? = null
    var deleted: Boolean? = false


    constructor()


    //Create
    @CommandHandler
    constructor(command: CreateMenSchemeCommand){

        AggregateLifecycle.apply(
            MenSchemeCreatedEvent(command.menSchemeId,command.schemeName,
                command.purposeOfScheme, command.accountNumber,command.nameOfPerson,
                command.phoneNumber, command.address,command.bankName,
                command.branchLocation, command.ifscCode,command.depositBalance,
                command.bankId,command.deleted)
        )
    }


    @EventSourcingHandler
    fun handlerForMenSchemeAccountCreatedEvent(createdEvent: MenSchemeCreatedEvent){
        if(accountNumber!= createdEvent.accountNumber) {
            this.menSchemeId = createdEvent.menSchemeId
            this.schemeName = createdEvent.schemeName
            this.purposeOfScheme = createdEvent.purposeOfScheme
            this.accountNumber = createdEvent.accountNumber
            this.nameOfPerson = createdEvent.nameOfPerson
            this.phoneNumber = createdEvent.phoneNumber
            this.address = createdEvent.address
            this.bankName = createdEvent.bankName
            this.branchLocation = createdEvent.branchLocation
            this.ifscCode = createdEvent.ifscCode
            this.depositBalance = createdEvent.depositBalance
            this.bankId = createdEvent.bankId
            this.deleted = createdEvent.deleted
        }
    }

    //Update
    @CommandHandler
    fun handlerForUpdateMenSchemeAccountCommand(command: UpdateMenSchemeCommand){
        AggregateLifecycle.apply(
            MenSchemeUpdatedEvent (command.menSchemeId,command.schemeName,
                command.purposeOfScheme,command.nameOfPerson,
                command.phoneNumber, command.address,command.bankName,
                command.branchLocation, command.ifscCode,command.depositBalance,
                command.bankId,command.deleted)
        )
    }


    @EventSourcingHandler
    fun handlerForMenSchemeAccountUpdatedEvent(updatedEvent: MenSchemeUpdatedEvent){

        this.schemeName = updatedEvent.schemeName
        this.purposeOfScheme = updatedEvent.purposeOfScheme
        this.nameOfPerson = updatedEvent.nameOfPerson
        this.phoneNumber = updatedEvent.phoneNumber
        this.address = updatedEvent.address
        this.bankName = updatedEvent.bankName
        this.branchLocation = updatedEvent.branchLocation
        this.ifscCode = updatedEvent.ifscCode
        this.depositBalance = updatedEvent.depositBalance
        this.bankId = updatedEvent.bankId
        this.deleted = updatedEvent.deleted
    }

    // Soft Deletion
    @CommandHandler
    fun handlerForDeleteMenSchemeAccount(command: DeleteMenSchemeCommand){
        AggregateLifecycle.apply(MenSchemeDeletedEvent(command.menSchemeId))
    }

    @EventSourcingHandler
    fun handlerForMenSchemeAccountDeletedEvent(deletedEvent: MenSchemeDeletedEvent){
        this.deleted=true
    }

    //Permanent Deletion
    @CommandHandler
    fun handlerForDeletePermanent(command: PermanentDeleteMenSchemeCommand){
        AggregateLifecycle.apply(MenSchemePermanentDeletedEvent(command.menSchemeId))
    }

    @EventSourcingHandler
    fun handlerForDeletePermanent(event: MenSchemePermanentDeletedEvent){

    }

    //restore
    @CommandHandler
    fun handlerForRestore(command: RestoreDeletedMenSchemeCommand){
        AggregateLifecycle.apply(DeletedMenSchemeRestoredEvent(command.menSchemeId))
    }

    @EventSourcingHandler
    fun handlerForRestoreEvent(restoredEvent: DeletedMenSchemeRestoredEvent){
        this.deleted=false
    }

}