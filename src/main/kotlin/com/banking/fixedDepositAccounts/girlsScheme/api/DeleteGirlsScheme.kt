package com.banking.fixedDepositAccounts.girlsScheme.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

//soft Deletion
data class DeleteGirlsSchemeCommand(
    @TargetAggregateIdentifier
    var girlsSchemeId: String? = null
)

data class GirlsSchemeDeletedEvent(
    var girlsSchemeId: String? = null
)

data class DeleteGirlsSchemeDTO(
    var girlsSchemeId: String? = null
)

//permanent Deletion
data class DeleteGirlsSchemePermanentCommand(
    @TargetAggregateIdentifier
    var girlsSchemeId: String? = null
)
data class GirlsSchemePermanentDeletedEvent(
    var girlsSchemeId: String? = null
)

data class PermanentDeleteGirlsSchemeDTO(
    var girlsSchemeId: String? = null
)

//Restoring purpose
data class RestoreGirlsSchemeAccountCommand(
    @TargetAggregateIdentifier
    var girlsSchemeId: String? = null
)
data class GirlsSchemeRestoredEvent(
    var girlsSchemeId: String? = null
)

data class RestoreGirlsSchemeDTO(
    var girlsSchemeId: String? = null
)