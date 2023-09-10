package com.banking.fixedDepositAccounts.women.query

import com.banking.accounts.query.CustomerAddress
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class WomenFixDeposit(
    @Id
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