package com.banking.fixedDepositAccounts.women.api

import com.banking.accounts.query.CustomerAddress
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateWomenFixDepositCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)

data class WomenFixDepositUpdatedEvent(
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)

data class UpdateWomenFixDepositDTO(
    var accountId: String? = null,
    var bankName: String? = null,
    var accountType: String? = "WomenFixedDeposit Scheme",
    var customerName: String? = null,
    var depositBalance: Double? = 0.0,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var customerAddress: CustomerAddress? = null,
    var deleted: Boolean? =false
)
