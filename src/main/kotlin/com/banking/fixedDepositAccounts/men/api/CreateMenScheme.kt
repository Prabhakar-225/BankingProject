package com.banking.fixedDepositAccounts.men.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateMenSchemeCommand(
    @TargetAggregateIdentifier
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var accountNumber: Long? = null,
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

data class MenSchemeCreatedEvent(
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var accountNumber: Long? = null,
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

data class CreateMenSchemeDTO(
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var accountNumber: Long? = null,
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

data class MenSchemeDto(
    var menSchemeId: String? = null,
    var schemeName: String? = "Men's Benefit Scheme",
    var purposeOfScheme: String? = null,
    var accountNumber: Long? = null,
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