package com.banking.bank.api

import com.banking.accounts.query.CustomerAddress
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AddAccountsToBankCommand(
    @TargetAggregateIdentifier
    var bankId: String? = null,
    var accountId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? =null
)

data class AccountsToBankAddedEvent(
    var bankId: String? = null,
    var accountId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? =null
)

data class AddAccountsToBankDTO(
    var bankId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null
)