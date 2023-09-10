package com.banking.loanAccounts.instant.command

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.instant.api.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class InstantLoanAggregate {

    @AggregateIdentifier
    var loanId: String? = null
    var consumerName: String? = null
    var consumerNumber: Long? = null
    var personAddress: Address? = null
    var personalDetails: IdentityDetails? = null
    var bankName: String? = null
    var accountNumber: Long? = null
    var ifscCode: String? = null
    var branchLocation: String? = null
    var sanctionedAmount: Double? = 0.0
    var loanInterest: Double? = 2.5
    var bankId: String? = null
    var deleted: Boolean? = false

    constructor()

    @CommandHandler
    constructor(command: CreateInstantLoanCommand){
        AggregateLifecycle.apply(InstantLoanCreatedEvent(command.loanId,command.consumerName,
            command.consumerNumber,command.personAddress,command.personalDetails,
            command.bankName,command.accountNumber,command.ifscCode,command.branchLocation,
            command.sanctionedAmount,command.loanInterest,command.bankId,command.deleted))
    }

    @EventSourcingHandler
    fun handlerForEvent(event: InstantLoanCreatedEvent){
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.personAddress = event.personAddress
        this.personalDetails = event.personalDetails
        this.bankName = event.bankName
        this.accountNumber = event.accountNumber
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.sanctionedAmount = event.sanctionedAmount
        this.loanInterest = event.loanInterest
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    @CommandHandler
    fun handlerForUpdateCommand(command: UpdateInstantLoanCommand){
        AggregateLifecycle.apply(InstantLoanUpdatedEvent(command.loanId,command.consumerName,
            command.consumerNumber,command.personAddress,command.personalDetails,
            command.bankName,command.ifscCode,command.branchLocation,
            command.sanctionedAmount,command.loanInterest,command.bankId,command.deleted))
    }

    @EventSourcingHandler
    fun handlerForUpdateEvent(event: InstantLoanUpdatedEvent){
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.personAddress = event.personAddress
        this.personalDetails = event.personalDetails
        this.bankName = event.bankName
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.sanctionedAmount = event.sanctionedAmount
        this.loanInterest = event.loanInterest
        this.bankId = event.bankId
        this.deleted = event.deleted
    }


    //Deleted Temporary
    @CommandHandler
    fun handlerForDeleteCommand(command: DeleteInstantLoanCommand){
        AggregateLifecycle.apply(InstantLoanDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForDeletedEvent(event: InstantLoanDeletedEvent){
        this.deleted = true
    }


    //Restore
    @CommandHandler
    fun handlerForRestoreDeletedCommand(command: RestoreDeletedInstantLoanCommand) {
        AggregateLifecycle.apply(DeletedInstantLoanRestoredEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForRestoreDeletedEvent(event: DeletedInstantLoanRestoredEvent) {
        this.deleted = false
    }


    //Delete Permanent
    @CommandHandler
    fun handlerForPermanentDeleteCommand(command: PermanentDeleteInstantLoanCommand) {
        AggregateLifecycle.apply(InstantLoanPermanentDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForPermanentDeletedEvent(event: InstantLoanPermanentDeletedEvent) {

    }

}