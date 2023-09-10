package com.banking.fixedDepositAccounts.jointFamily.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

// temporary delete
data class DeleteJointFamilyCommand(
    @TargetAggregateIdentifier
    var jointFamilyId: String? = null
)

data class JointFamilyDeletedEvent(
    var jointFamilyId: String? = null
)

data class DeleteJointFamilyDTO(
    var jointFamilyId: String? = null
)

//restore deleted record
data class RestoreJointFamilyCommand(
    @TargetAggregateIdentifier
    var jointFamilyId: String? = null
)

data class JointFamilyRestoredEvent(
    var jointFamilyId: String? = null
)

//permanent delete
data class DeletePermanentJointFamilyCommand(
    @TargetAggregateIdentifier
    var jointFamilyId: String? = null
)

data class JointFamilyPermanentDeletedEvent(
    var jointFamilyId: String? = null
)