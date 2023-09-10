package com.banking.fixedDepositAccounts.girlsScheme.query

import com.banking.accounts.query.CustomerAddress
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class GirlsScheme (
    @Id
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

data class Address(
    var permanentAddress: String? = null,
    var currentAddress: String? = null
)