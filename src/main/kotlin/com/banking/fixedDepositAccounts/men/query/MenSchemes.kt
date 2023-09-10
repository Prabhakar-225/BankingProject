package com.banking.fixedDepositAccounts.men.query

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class MenSchemes(
    @Id
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