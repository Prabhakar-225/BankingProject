package com.banking.fixedDepositAccounts.women.api

import com.banking.accounts.query.CustomerAddress
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateWomenFixDepositCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)

data class WomenFixDepositCreatedEvent(
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)

data class CreateWomenFixDepositDTO(
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)

data class WomenFixedDepositDto(
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)