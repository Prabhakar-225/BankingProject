package com.banking.bank.api

import com.banking.bank.query.Accounts
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateBankCommand(
    @TargetAggregateIdentifier
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false
)

data class BankUpdatedEvent(
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false
)

data class UpdateBankDto(
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false
)