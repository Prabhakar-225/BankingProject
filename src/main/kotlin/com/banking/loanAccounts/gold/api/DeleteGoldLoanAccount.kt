package com.banking.loanAccounts.gold.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteGoldLoanAccountCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class GoldLoanAccountDeletedEvent(
    var loanId: String? = null,
)

data class DeleteGoldLoanAccountDTO(
    var loanId: String? = null
)

data class PermanentDeleteGoldLoanAccountCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class GoldLoanAccountPermanentDeletedEvent(
    var loanId: String? = null
)

data class RestoreDeletedGoldLoanAccountCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class DeletedGoldLoanAccountRestoredEvent(
    var loanId: String? = null
)