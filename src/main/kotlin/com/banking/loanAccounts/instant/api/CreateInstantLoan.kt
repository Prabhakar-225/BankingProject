package com.banking.loanAccounts.instant.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateInstantLoanCommand(
    @TargetAggregateIdentifier
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

data class InstantLoanCreatedEvent(
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

data class CreateInstantLoanDTO(
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

data class InstantLoanDto(
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