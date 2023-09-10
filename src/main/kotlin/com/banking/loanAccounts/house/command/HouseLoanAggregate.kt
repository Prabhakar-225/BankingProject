package com.banking.loanAccounts.house.command

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.gold.api.DeletedGoldLoanAccountRestoredEvent
import com.banking.loanAccounts.house.api.*
import com.banking.loanAccounts.house.query.HouseDetails
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class HouseLoanAggregate {

    @AggregateIdentifier
    var loanId: String? = null
    var consumerName: String? = null
    var consumerNumber: Long? = null
    var consumerAddress: Address? = null
    var identityDetails: IdentityDetails? = null
    var houseDetails: HouseDetails? = null
    var houseCents: Number? = 0
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
    constructor(command: CreateHouseLoanCommand) {
        AggregateLifecycle.apply(
            HouseLoanCreatedEvent(
                command.loanId, command.consumerName, command.consumerNumber,
                command.consumerAddress, command.identityDetails, command.houseDetails,
                command.houseCents, command.bankName, command.accountNumber, command.ifscCode,
                command.branchLocation, command.sanctionedAmount, command.loanInterest,
                command.bankId, command.deleted
            )
        )
    }

    @EventSourcingHandler
    fun handlerForHouseLoanCreatedEvent(event: HouseLoanCreatedEvent) {
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.consumerAddress = event.consumerAddress
        this.identityDetails = event.identityDetails
        this.houseDetails = event.houseDetails
        this.houseCents = event.houseCents
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
    fun handlerForHouseLoanUpdateCommand(command: UpdateHouseLoanCommand) {
        AggregateLifecycle.apply(
            HouseLoanUpdatedEvent(
                command.loanId, command.consumerName, command.consumerNumber,
                command.consumerAddress, command.identityDetails, command.houseDetails,
                command.houseCents, command.bankName, command.ifscCode,
                command.branchLocation, command.sanctionedAmount, command.loanInterest,
                command.bankId, command.deleted
            )
        )
    }

    @EventSourcingHandler
    fun handlerForHouseLoanUpdatedEvent(event: HouseLoanUpdatedEvent) {
        this.loanId = event.loanId
        this.consumerName = event.consumerName
        this.consumerNumber = event.consumerNumber
        this.consumerAddress = event.consumerAddress
        this.identityDetails = event.identityDetails
        this.houseDetails = event.houseDetails
        this.houseCents = event.houseCents
        this.bankName = event.bankName
        this.ifscCode = event.ifscCode
        this.branchLocation = event.branchLocation
        this.sanctionedAmount = event.sanctionedAmount
        this.loanInterest = event.loanInterest
        this.bankId = event.bankId
        this.deleted = event.deleted
    }

    @CommandHandler
    fun deleteCommand(command: DeleteHouseLoanCommand) {
        AggregateLifecycle.apply(HouseLoanDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun deletedEvent(event: HouseLoanDeletedEvent) {
        this.deleted = true
    }

    @CommandHandler
    fun restoreDeletedCommand(command: RestoreDeletedHouseLoanCommand) {
        AggregateLifecycle.apply(DeletedHouseLoanRestoredEvent(command.loanId))
    }

    @EventSourcingHandler
    fun restoreDeletedEvent(event: DeletedHouseLoanRestoredEvent) {
        this.deleted = false
    }

    @CommandHandler
    fun permanentDeleteCommand(command: PermanentDeleteHouseLoanCommand) {
        AggregateLifecycle.apply(HouseLoanPermanentDeletedEvent(command.loanId))
    }

    @EventSourcingHandler
    fun permanentDeletedEvent(event: HouseLoanPermanentDeletedEvent) {

    }

}