package com.banking.bank.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteBankCommand(
    @TargetAggregateIdentifier
    var bankId: String? = null
)

data class BankDeletedEvent(
    var bankId: String? = null
)

data class DeleteBankDto(
    var bankId: String? = null
)

data class PermanentDeleteBankCommand(
    @TargetAggregateIdentifier
    var bankId: String? = null
)

data class BankPermanentDeletedEvent(
    var bankId: String? = null
)
