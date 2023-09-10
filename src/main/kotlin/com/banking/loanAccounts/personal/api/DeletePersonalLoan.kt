package com.banking.loanAccounts.personal.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeletePersonalLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class PersonalLoanDeletedEvent(
    var loanId: String? = null,
)

data class DeletePersonalLoanDTO(
    var loanId: String? = null,
)

data class RestorePersonalLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class PersonalLoanRestoredEvent(
    var loanId: String? = null,
)

data class PermanentDeletePersonalLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class PersonalLoanPermanentDeletedEvent(
    var loanId: String? = null,
)