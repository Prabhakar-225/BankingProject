package com.banking.bank.command

import com.banking.accounts.api.*
import com.banking.bank.api.*
import com.banking.bank.query.Accounts
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class BanksAggregate {

    @AggregateIdentifier
    private var bankId: String? = null
    private var bankName: String? = null
    private var accountDetails: List<Accounts>? = emptyList()
    private var bankLocation: String? = null
    private var ifscCode: String? = null
    private var accounts: Long? = 0
    private var bankHeadOffice: String? = null
    private var bankDeleted: Boolean? = false


    constructor()

    //Create
    @CommandHandler
    constructor(command: CreateBankCommand) {
        AggregateLifecycle.apply(
            BankCreatedEvent(
                command.bankId, command.bankName,
                command.accountDetails?.map { x ->
                    Accounts(
                        x.accountId, x.accountType, x.accountNumber,
                        x.customerName, x.balance, x.branchLocation, x.deleted
                    )
                }, command.bankLocation,
                command.ifscCode, command.accounts,
                command.bankHeadOffice, command.bankDeleted
            )
        )
    }


    @EventSourcingHandler
    fun handler(createdEvent: BankCreatedEvent) {

        this.bankId = createdEvent.bankId
        this.bankName = createdEvent.bankName
        this.accountDetails = createdEvent.accountDetails
            ?.map { x ->
                Accounts(
                    x.accountId, x.accountType, x.accountNumber, x.customerName,
                    x.balance, x.branchLocation, x.deleted
                )
            }
        this.bankLocation = createdEvent.bankLocation
        this.ifscCode = createdEvent.ifscCode
        this.accounts = createdEvent.accounts
        this.bankHeadOffice = createdEvent.bankHeadOffice
        this.bankDeleted = createdEvent.bankDeleted

    }

    //Update
    @CommandHandler
    fun handler(command: UpdateBankCommand) {
        AggregateLifecycle.apply(
            BankUpdatedEvent(
                command.bankId, command.bankName,
                command.accountDetails, command.bankLocation,
                command.ifscCode, command.accounts,
                command.bankHeadOffice, command.bankDeleted
            )
        )
    }


    @EventSourcingHandler
    fun handler(updatedEvent: BankUpdatedEvent) {

        this.bankName = updatedEvent.bankName
        this.accountDetails = updatedEvent.accountDetails
            ?.map { x ->
                Accounts(
                    x.accountId, x.accountType, x.accountNumber, x.customerName,
                    x.balance, x.branchLocation, x.deleted
                )
            }
        this.bankLocation = updatedEvent.bankLocation
        this.ifscCode = updatedEvent.ifscCode
        this.accounts = updatedEvent.accounts
        this.bankHeadOffice = updatedEvent.bankHeadOffice
        this.bankDeleted = updatedEvent.bankDeleted
    }

    // Soft Deletion
    @CommandHandler
    fun handler(command: DeleteBankCommand) {
        AggregateLifecycle.apply(BankDeletedEvent(command.bankId))
    }

    @EventSourcingHandler
    fun handler(deletedEvent: AccountDeletedEvent) {
        this.bankDeleted = true
    }

    @CommandHandler
    fun handler(command: PermanentDeleteBankCommand){
        AggregateLifecycle.apply(BankPermanentDeletedEvent(command.bankId))
    }

    @EventSourcingHandler
    fun handler(event: BankPermanentDeletedEvent){

    }

    @CommandHandler
    fun add(command: AddAccountsToBankCommand){
        AggregateLifecycle.apply(AccountsToBankAddedEvent(command.bankId,command.accountId,
            command.bankName,command.bankBranchLocation,command.ifscCode,command.accountType,
            command.accountNumber,command.customerName,command.customerAddress,
            command.balance,command.deleted))
    }

    @EventSourcingHandler
    fun add(event: AccountsToBankAddedEvent){

        this.accountDetails = (accountDetails?: emptyList()).plus(
            Accounts(
           event.accountId,
            event.accountType,
            event.accountNumber,event.customerName,
            event.balance,event.bankBranchLocation,event.deleted))
    }

}