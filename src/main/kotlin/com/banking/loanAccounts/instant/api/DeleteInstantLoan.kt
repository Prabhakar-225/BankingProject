package com.banking.loanAccounts.instant.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteInstantLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null)

data class InstantLoanDeletedEvent(
    var loanId: String? = null,
)

data class DeleteInstantLoanDTO(
    var loanId: String? = null,
)

data class RestoreDeletedInstantLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class DeletedInstantLoanRestoredEvent(
    var loanId: String? = null,
)

data class PermanentDeleteInstantLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class InstantLoanPermanentDeletedEvent(
    var loanId: String? = null,
)