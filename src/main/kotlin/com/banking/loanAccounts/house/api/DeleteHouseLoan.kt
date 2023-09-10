package com.banking.loanAccounts.house.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteHouseLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class HouseLoanDeletedEvent(
    var loanId: String? = null
)

data class DeleteHouseLoanDTO(
    var loanId: String? = null
)

data class PermanentDeleteHouseLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class HouseLoanPermanentDeletedEvent(
    var loanId: String? = null
)

data class RestoreDeletedHouseLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null
)

data class DeletedHouseLoanRestoredEvent(
    var loanId: String? = null
)