package com.banking.loanAccounts.gold.command

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.gold.api.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate


@Aggregate
class GoldLoanAggregate {

    @AggregateIdentifier
    var loanId: String? = null
    var consumerName: String? = null
    var consumerNumber: Long? = null
    var consumerAddress: Address? = null
    var identityDetails: IdentityDetails? = null
    var goldGrams: Double? = 0.0
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
    constructor(command: CreateGoldLoanAccountCommand) {
        AggregateLifecycle.apply(
            GoldLoanAccountCreatedEvent(
                command.loanId, command.consumerName, command.consumerNumber,
                command.consumerAddress, command.identityDetails, command.goldGrams,
                command.bankName, command.accountNumber, command.ifscCode,
                command.branchLocation, command.sanctionedAmount, command.loanInterest,
                command.bankId, command.deleted
            )
        )
    }

    @EventSourcingHandler
    fun handlerForGoldLoanCreatedEvent(event: GoldLoanAccountCreatedEvent) {
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.consumerAddress = event.consumerAddress
        this.identityDetails = event.identityDetails
        this.goldGrams = event.goldGrams
        this.bankName = event.bankName
        this.accountNumber = event.accountNumber
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.sanctionedAmount = event.sanctionedAmount
        this.loanInterest = event.loanInterest
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    // Update
    @CommandHandler
    fun handlerForUpdateGoldLoanAccount(command: UpdateGoldLoanAccountCommand) {
        AggregateLifecycle.apply(
            GoldLoanAccountUpdatedEvent(
                command.loanId, command.consumerName, command.consumerNumber,
                command.consumerAddress, command.identityDetails, command.goldGrams,
                command.bankName, command.ifscCode, command.branchLocation,
                command.sanctionedAmount, command.loanInterest, command.bankId,
                command.deleted
            ))
    }

    @EventSourcingHandler
    fun handlerForGoldLoanUpdatedEvent(event: GoldLoanAccountUpdatedEvent){
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.consumerAddress = event.consumerAddress
        this.identityDetails = event.identityDetails
        this.goldGrams = event.goldGrams
        this.bankName = event.bankName
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.sanctionedAmount = event.sanctionedAmount
        this.loanInterest = event.loanInterest
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    //Delete
    @CommandHandler
    fun handlerForDelete(command: DeleteGoldLoanAccountCommand){
        AggregateLifecycle.apply(GoldLoanAccountDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForDeleteEvent(event: GoldLoanAccountDeletedEvent){
        this.deleted = true
    }

    //Restore
    @CommandHandler
    fun handlerForRestore(command: RestoreDeletedGoldLoanAccountCommand){
        AggregateLifecycle.apply(DeletedGoldLoanAccountRestoredEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForRestoreEvent(event: DeletedGoldLoanAccountRestoredEvent){
        this.deleted = false
    }

    //PermanentDelete
    @CommandHandler
    fun handlerForPermanentDelete(command: PermanentDeleteGoldLoanAccountCommand){
        AggregateLifecycle.apply(GoldLoanAccountPermanentDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun handlerForPermanentDeleteEvent(event: GoldLoanAccountPermanentDeletedEvent){

    }


}