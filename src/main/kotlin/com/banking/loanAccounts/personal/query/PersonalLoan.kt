package com.banking.loanAccounts.personal.query

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PersonalLoan(
    @Id
    var loanId: String? = null,
    var personName: String? = null,
    var personNumber: Long? = null,
    var personAddress: Address? = null,
    var personalDetails: IdentityDetails? = null,
    var qualificationDetails: Qualification? = null,
    var bankName: String? = null,
    var accountNumber: Long? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var grantedAmount: Double? = 0.0,
    var loanInterest: Double? = 1.5,
    var loanTimePeriod: String? = "5 Years",
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class Qualification(
    var qualification: String? =null,
    var currentWork: String? = null,
    var purposeOfLoan: String? = null,
)
