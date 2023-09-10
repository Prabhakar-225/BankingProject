package com.banking.fixedDepositAccounts.jointFamily.command

import com.banking.fixedDepositAccounts.jointFamily.api.*
import com.banking.fixedDepositAccounts.jointFamily.query.PersonNames
import com.banking.fixedDepositAccounts.jointFamily.query.PersonsPhoneNumbers
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class JointFamilyAggregate {

    @AggregateIdentifier
    var jointFamilyId: String? = null
    var accountNumber: Long? = null
    var schemeName: String? ="JointFamily Scheme"
    var jointFamilyName: String? = null
    var jointFamilyAddress: String? = null
    var personsToInvest: Number? =0
    var personsNames: PersonNames? = null
    var personsPhoneNumbers: PersonsPhoneNumbers? = null
    var bankName: String? =null
    var branchLocation: String? = null
    var ifscCode: String? = null
    var depositMoney: Double? = null
    var bankId: String? = null
    var deleted: Boolean? = false

    constructor()


    //Create
    @CommandHandler
    constructor(command: CreateJointFamilyCommand){

        AggregateLifecycle.apply(
            JointFamilyCreatedEvent(command.jointFamilyId,command.accountNumber,
                command.schemeName, command.jointFamilyName,command.jointFamilyAddress,
                command.personsToInvest, command.personsNames,command.personsPhoneNumbers,
                command.bankName, command.branchLocation,command.ifscCode,command.depositMoney,
                command.bankId,command.deleted)
        )
    }


    @EventSourcingHandler
    fun handlerForJointFamilyAccountCreatedEvent(createdEvent: JointFamilyCreatedEvent){
        if(accountNumber!= createdEvent.accountNumber) {
            this.jointFamilyId = createdEvent.jointFamilyId
            this.accountNumber = createdEvent.accountNumber
            this.schemeName = createdEvent.schemeName
            this.jointFamilyName = createdEvent.jointFamilyName
            this.jointFamilyAddress = createdEvent.jointFamilyAddress
            this.personsToInvest = createdEvent.personsToInvest
            this.personsNames = createdEvent.personsNames
            this.personsPhoneNumbers = createdEvent.personsPhoneNumbers
            this.bankName = createdEvent.bankName
            this.branchLocation = createdEvent.branchLocation
            this.ifscCode = createdEvent.ifscCode
            this.depositMoney = createdEvent.depositMoney
            this.bankId = createdEvent.bankId
            this.deleted = createdEvent.deleted
        }
    }

    //Update
    @CommandHandler
    fun handlerForUpdateJointFamilyAccountCommand(command: UpdateJointFamilyCommand){
        AggregateLifecycle.apply(
            JointFamilyUpdatedEvent (command.jointFamilyId, command.schemeName,
                command.jointFamilyName,command.jointFamilyAddress,
                command.personsToInvest, command.personsNames,command.personsPhoneNumbers,
                command.bankName, command.branchLocation,command.ifscCode,command.depositMoney,
                command.bankId,command.deleted)
        )
    }


    @EventSourcingHandler
    fun handlerForJointFamilyAccountUpdatedEvent(updatedEvent: JointFamilyUpdatedEvent){

        this.schemeName = updatedEvent.schemeName
        this.jointFamilyName = updatedEvent.jointFamilyName
        this.jointFamilyAddress = updatedEvent.jointFamilyAddress
        this.personsToInvest = updatedEvent.personsToInvest
        this.personsNames = updatedEvent.personsNames
        this.personsPhoneNumbers = updatedEvent.personsPhoneNumbers
        this.bankName = updatedEvent.bankName
        this.branchLocation = updatedEvent.branchLocation
        this.ifscCode = updatedEvent.ifscCode
        this.depositMoney = updatedEvent.depositMoney
        this.bankId = updatedEvent.bankId
        this.deleted = updatedEvent.deleted
    }

    // Soft Deletion
    @CommandHandler
    fun handlerForDeleteJointFamilyAccount(command: DeleteJointFamilyCommand){
        AggregateLifecycle.apply(JointFamilyDeletedEvent(command.jointFamilyId))
    }

    @EventSourcingHandler
    fun handlerForJointFamilyAccountDeletedEvent(deletedEvent: JointFamilyDeletedEvent){
        this.deleted=true
    }

    //Permanent Deletion
    @CommandHandler
    fun handlerForDeletePermanent(command: DeletePermanentJointFamilyCommand){
        AggregateLifecycle.apply(JointFamilyPermanentDeletedEvent(command.jointFamilyId))
    }

    @EventSourcingHandler
    fun handlerForDeletePermanent(event: JointFamilyPermanentDeletedEvent){

    }

    //restore
    @CommandHandler
    fun handlerForRestore(command: RestoreJointFamilyCommand){
        AggregateLifecycle.apply(JointFamilyRestoredEvent(command.jointFamilyId))
    }

    @EventSourcingHandler
    fun handlerForRestoreEvent(restoredEvent: JointFamilyRestoredEvent){
        this.deleted=false
    }
}