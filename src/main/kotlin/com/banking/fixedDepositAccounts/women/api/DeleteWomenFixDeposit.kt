package com.banking.fixedDepositAccounts.women.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteWomenFixDepositCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null
)

data class WomenFixDepositDeletedEvent(
    var accountId: String? = null
)

data class WomenFixDepositDTO(
    var accountId: String? = null
)

data class DeletePermanentWomenFixDepositCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null
)

data class WomenFixDepositPermanentDeletedEvent(
    var accountId: String? = null
)

data class RestoreWomenFixDepositCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null
)

data class WomenFixDepositRestoredEvent(
    var accountId: String? = null
)
