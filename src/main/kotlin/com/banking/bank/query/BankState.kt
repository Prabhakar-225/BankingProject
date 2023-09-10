package com.banking.bank.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class BankState (
    @Id
    var bankId: String? = null,
    var bankName: String? = null,
    var accountDetails: List<Accounts>? = emptyList(),
    var bankLocation: String? = null,
    var ifscCode: String? = null,
    var accounts: Long? = 0,
    var bankHeadOffice: String? = null,
    var bankDeleted: Boolean? = false

)

data class Accounts(
    var accountId: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var balance: Double? = null,
    var branchLocation: String? = null,
    var deleted: Boolean? =false
)

