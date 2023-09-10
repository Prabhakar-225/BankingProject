package com.banking.loanAccounts.personal.command

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.personal.api.*
import com.banking.loanAccounts.personal.query.Qualification
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class PersonalLoanAggregate {

    @AggregateIdentifier
    private var loanId: String? = null
    private var personName: String? = null
    private var personNumber: Long? = null
    private var personAddress: Address? = null
    private var personalDetails: IdentityDetails? = null
    private var qualificationDetails: Qualification? = null
    private var bankName: String? = null
    private var accountNumber: Long? = null
    private var ifscCode: String? = null
    private var branchLocation: String? = null
    private var grantedAmount: Double? = 0.0
    private var loanInterest: Double? = 1.5
    private var loanTimePeriod: String? = "5 Years"
    private var bankId: String? = null
    private var deleted: Boolean? = false


    constructor()

    @CommandHandler
    constructor(command: CreatePersonalLoanCommand){
        AggregateLifecycle.apply(PersonalLoanCreatedEvent(command.loanId,command.personName,
            command.personNumber,command.personAddress,command.personalDetails,
            command.qualificationDetails,command.bankName,command.accountNumber,
            command.ifscCode,command.branchLocation,command.grantedAmount,command.loanInterest,
            command.loanTimePeriod,command.bankId,command.deleted))
    }

    @EventSourcingHandler
    fun handlerForCreateEvent(event: PersonalLoanCreatedEvent){
        this.loanId = event.loanId
        this.personName = event.personName
        this.personNumber = event.personNumber
        this.personAddress = event.personAddress
        this.personalDetails = event.personalDetails
        this.qualificationDetails = event.qualificationDetails
        this.bankName = event.bankName
        this.accountNumber = event.accountNumber
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.grantedAmount = event.grantedAmount
        this.loanInterest = event.loanInterest
        this.loanTimePeriod = event.loanTimePeriod
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    @CommandHandler
    fun handlerForUpdate(command: UpdatePersonalLoanCommand){
        AggregateLifecycle.apply(PersonalLoanUpdatedEvent(command.loanId,command.personName,
            command.personNumber,command.personAddress,command.personalDetails,
            command.qualificationDetails,command.bankName,command.ifscCode,
            command.branchLocation,command.grantedAmount,command.loanInterest,
            command.loanTimePeriod,command.bankId,command.deleted))
    }

    @EventSourcingHandler
    fun handlerForUpdateEvent(event: PersonalLoanUpdatedEvent){
        this.loanId = event.loanId
        this.personName = event.personName
        this.personNumber = event.personNumber
        this.personAddress = event.personAddress
        this.personalDetails = event.personalDetails
        this.qualificationDetails = event.qualificationDetails
        this.bankName = event.bankName
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.grantedAmount = event.grantedAmount
        this.loanInterest = event.loanInterest
        this.loanTimePeriod = event.loanTimePeriod
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    @CommandHandler
    fun handlerForDeleteTemporary(command: DeletePersonalLoanCommand){
        AggregateLifecycle.apply(PersonalLoanDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForDeleteEvent(event: PersonalLoanDeletedEvent){
        this.deleted = true
    }

    @CommandHandler
    fun handlerForDeletePermanent(command: PermanentDeletePersonalLoanCommand){
        AggregateLifecycle.apply(PersonalLoanPermanentDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForDeleteEvent(event: PersonalLoanPermanentDeletedEvent){

    }

    @CommandHandler
    fun handlerForRestore(command: RestorePersonalLoanCommand){
        AggregateLifecycle.apply(PersonalLoanRestoredEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForRestoreEvent(event: PersonalLoanRestoredEvent){
        this.deleted = false
    }


}