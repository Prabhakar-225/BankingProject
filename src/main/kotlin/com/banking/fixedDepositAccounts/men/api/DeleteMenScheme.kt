package com.banking.fixedDepositAccounts.men.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteMenSchemeCommand(
    @TargetAggregateIdentifier
    var menSchemeId: String? = null,
)

data class MenSchemeDeletedEvent(
    var menSchemeId: String? = null,
)

data class DeleteMenSchemeDTO(
    var menSchemeId: String? = null,
)

data class RestoreDeletedMenSchemeCommand(
    @TargetAggregateIdentifier
    var menSchemeId: String? = null,
)

data class DeletedMenSchemeRestoredEvent(
    var menSchemeId: String? = null,
)

data class PermanentDeleteMenSchemeCommand(
    @TargetAggregateIdentifier
    var menSchemeId: String? = null,
)

data class MenSchemePermanentDeletedEvent(
    var menSchemeId: String? = null,
)