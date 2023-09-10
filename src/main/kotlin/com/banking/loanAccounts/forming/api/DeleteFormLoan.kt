package com.banking.loanAccounts.forming.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteFormLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class FormLoanDeletedEvent(
    var loanId: String? = null,
)

data class DeleteFormLoanDTO(
    var loanId: String? = null,
)

data class RestoreDeletedFormLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class DeletedFormLoanRestoredEvent(
    var loanId: String? = null,
)

data class PermanentDeleteFormLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
)

data class FormLoanPermanentDeletedEvent(
    var loanId: String? = null,
)