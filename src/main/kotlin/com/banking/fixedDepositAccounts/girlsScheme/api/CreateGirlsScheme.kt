package com.banking.fixedDepositAccounts.girlsScheme.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateGirlsSchemeCommand(
    @TargetAggregateIdentifier
    var girlsSchemeId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "GirlsBenefit Scheme",
    var schemeName: String? = null,
    var accountNumber: Long? = 0L,
    var girlName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var address: Address? = null,
    var deleted: Boolean? =false,
    var bankId: String? = null
)

data class GirlsSchemeCreatedEvent(
    var girlsSchemeId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "GirlsBenefit Scheme",
    var schemeName: String? = null,
    var accountNumber: Long? = 0L,
    var girlName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var address: Address? = null,
    var deleted: Boolean? =false,
    var bankId: String? = null
)

data class CreateGirlsSchemeDTO(
    var girlsSchemeId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "GirlsBenefit Scheme",
    var schemeName: String? = null,
    var accountNumber: Long? = 0L,
    var girlName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var address: Address? = null,
    var deleted: Boolean? =false,
    var bankId: String? = null
)

data class GirlsSchemeDto(
    var girlsSchemeId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "GirlsBenefit Scheme",
    var schemeName: String? = null,
    var accountNumber: Long? = 0L,
    var girlName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var address: Address? = null,
    var deleted: Boolean? =false,
    var bankId: String? = null
)
