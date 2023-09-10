package com.banking.loanAccounts.house.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.house.query.HouseDetails
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateHouseLoanCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var houseDetails: HouseDetails? = null,
    var houseCents: Number? = 0,
    var bankName: String? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class HouseLoanUpdatedEvent(
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var houseDetails: HouseDetails? = null,
    var houseCents: Number? = 0,
    var bankName: String? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class UpdateHouseLoanDTO(
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var houseDetails: HouseDetails? = null,
    var houseCents: Number? = 0,
    var bankName: String? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)