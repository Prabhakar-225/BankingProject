package com.banking.fixedDepositAccounts.men.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateMenSchemeCommand(
    @TargetAggregateIdentifier
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var nameOfPerson: String? = null,
    var phoneNumber: Long? = null,
    var address: Address? = null,
    var bankName: String? = null,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var depositBalance: Double? = 0.0,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class MenSchemeUpdatedEvent(
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var nameOfPerson: String? = null,
    var phoneNumber: Long? = null,
    var address: Address? = null,
    var bankName: String? = null,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var depositBalance: Double? = 0.0,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class UpdateMenSchemeDTO(
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var nameOfPerson: String? = null,
    var phoneNumber: Long? = null,
    var address: Address? = null,
    var bankName: String? = null,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var depositBalance: Double? = 0.0,
    var bankId: String? = null,
    var deleted: Boolean? = false
)