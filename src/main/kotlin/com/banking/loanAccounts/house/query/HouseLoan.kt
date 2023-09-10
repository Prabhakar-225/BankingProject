package com.banking.loanAccounts.house.query

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class HouseLoan(
    @Id
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var houseDetails: HouseDetails? = null,
    var houseCents: Number? = 0,
    var bankName: String? = null,
    var accountNumber: Long? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class HouseDetails(
    var locationOfHouse: String? = null,
    var houseRegisteredName: String? = null,
    var nameOfFather: String? = null,
    var houseSurveyNumber: String? = null,
    var valueOfLand: String? = null
)
