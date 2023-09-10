package com.banking.accounts.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class AccountState(
    @Id
    var accountId: String? = null,
    var bankId: String? = null,
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

data class CustomerAddress(
    var permanentAddress: String? = null,
    var currentAddress: String? = null
)