package com.banking.loanAccounts.gold.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateGoldLoanAccountCommand(
    @TargetAggregateIdentifier
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var goldGrams: Double? = 0.0,
    var bankName: String? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class GoldLoanAccountUpdatedEvent(
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var goldGrams: Double? = 0.0,
    var bankName: String? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class UpdateGoldLoanAccountDTO(
    var loanId: String? = null,
    var consumerName: String? = null,
    var consumerNumber: Long? = null,
    var consumerAddress: Address? = null,
    var identityDetails: IdentityDetails? = null,
    var goldGrams: Double? = 0.0,
    var bankName: String? = null,
    var ifscCode: String? = null,
    var branchLocation: String? = null,
    var sanctionedAmount: Double? = 0.0,
    var loanInterest: Double? = 2.5,
    var bankId: String? = null,
    var deleted: Boolean? = false
)