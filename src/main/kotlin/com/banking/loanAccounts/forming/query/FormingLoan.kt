package com.banking.loanAccounts.forming.query

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class FormingLoan(
    @Id
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var landDetails: LandDetails? = null,
    var bankName: String? = null,
    var accountNumber: Long? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class IdentityDetails(
    var aadhaarNumber: String? = null,
    var panCardNumber: String? = null,
    var mobileNumber: String? = null,
    var voterId : String? = null
)

data class LandDetails(
    var locationOfLand: String? = null,
    var nameOfFather: String? = null,
    var surveyNumber: String? = null,
    var natureOfLand: String? = null,
    var valueOfLand: String? = null
)