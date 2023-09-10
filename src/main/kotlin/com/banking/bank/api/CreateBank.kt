package com.banking.bank.api

import com.banking.bank.query.Accounts

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateBankCommand(
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

data class BankCreatedEvent(
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false
)

data class CreateBankDTO(
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false
)

data class BankDto(
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false
)

data class AccountsDto(
    var accountId: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var balance: Double? = null,
    var branchLocation: String? = null,
    var deleted: Boolean? =false
)



