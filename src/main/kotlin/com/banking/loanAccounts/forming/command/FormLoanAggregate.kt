package com.banking.loanAccounts.forming.command

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.api.*
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.forming.query.LandDetails
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate


@Aggregate
class FormLoanAggregate {

    @AggregateIdentifier
    var loanId: String? = null
    var consumerName: String? = null
    var consumerNumber: Long? = null
    var consumerAddress: Address? = null
    var identityDetails: IdentityDetails? = null
    var landDetails: LandDetails? = null
    var bankName: String? = null
    var accountNumber: Long? = null
    var ifscCode: String? = null
    var branchLocation: String? = null
    var sanctionedAmount: Double? = 0.0
    var loanInterest: Double? = 2.5
    var bankId: String? = null
    var deleted: Boolean? = false


    constructor()

    //Create
    @CommandHandler
    constructor(command: CreateFormLoanCommand) {
        AggregateLifecycle.apply(
            FormLoanCreatedEvent(
               command.loanId, command.consumerName, command.consumerNumber,
                command.consumerAddress, command.identityDetails, command.landDetails,
                command.bankName, command.accountNumber,command.ifscCode,command.branchLocation,
                command.sanctionedAmount, command.loanInterest, command.bankId, command.deleted
            )
        )
    }

    @EventSourcingHandler
    fun handlerForFormLoanCreatedEvent(event: FormLoanCreatedEvent) {

            this.loanId = event.loanId
            this.consumerName = event.consumerName
            this.consumerNumber = event.consumerNumber
            this.consumerAddress = event.consumerAddress
            this.identityDetails = event.identityDetails
            this.landDetails = event.landDetails
            this.bankName = event.bankName
            this.accountNumber = event.accountNumber
            this.ifscCode = event.ifscCode
            this.branchLocation = event.branchLocation
            this.sanctionedAmount = event.sanctionedAmount
            this.loanInterest = event.loanInterest
            this.bankId = event.bankId
            this.deleted = event.deleted
    }

    //Update
    @CommandHandler
    fun handlerForUpdateFormLoan(command: UpdateFormLoanCommand){
        AggregateLifecycle.apply(
            FormLoanUpdatedEvent(
                command.loanId, command.consumerName, command.consumerNumber,
                command.consumerAddress, command.identityDetails, command.landDetails,
                command.bankName,command.ifscCode,command.branchLocation,
                command.sanctionedAmount, command.loanInterest, command.bankId, command.deleted
            )
        )
    }

    @EventSourcingHandler
    fun handlerForFormLoanUpdatedEvent(event: FormLoanUpdatedEvent){
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.consumerAddress = event.consumerAddress
        this.identityDetails = event.identityDetails
        this.landDetails = event.landDetails
        this.bankName = event.bankName
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.sanctionedAmount = event.sanctionedAmount
        this.loanInterest = event.loanInterest
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    //Soft Deletion
    @CommandHandler
    fun handlerForTemporaryDeletion(command: DeleteFormLoanCommand){
        AggregateLifecycle.apply(
            FormLoanDeletedEvent(command.loanId)
        )
    }

    @EventSourcingHandler
    fun handlerForTemporaryDeletionEvent(event: FormLoanDeletedEvent){
        this.deleted = true
    }

    //Restore
    @CommandHandler
    fun handlerForRestore(command: RestoreDeletedFormLoanCommand){
        AggregateLifecycle.apply(
            DeletedFormLoanRestoredEvent(command.loanId)
        )
    }

    @EventSourcingHandler
    fun handlerForRestore(event: DeletedFormLoanRestoredEvent){
        this.deleted = false
    }

    //Hard Deletion
    @CommandHandler
    fun handlerForPermanentDeletion(command: PermanentDeleteFormLoanCommand){
        AggregateLifecycle.apply(
            FormLoanPermanentDeletedEvent(command.loanId)
        )
    }

    @EventSourcingHandler
    fun handlerForPermanentDeletion(event: FormLoanPermanentDeletedEvent){

    }

}