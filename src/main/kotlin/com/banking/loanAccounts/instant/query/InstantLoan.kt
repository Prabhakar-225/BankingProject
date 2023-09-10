package com.banking.loanAccounts.instant.query

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class InstantLoan(

    @Id
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var personAddress: Address? = null,
    var personalDetails: IdentityDetails? = null,
    var bankName: String? = null,
    var accountNumber: Long? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)
