package com.banking.loanAccounts.forming.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.forming.query.LandDetails
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateFormLoanCommand(
    @TargetAggregateIdentifier
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

data class FormLoanCreatedEvent(
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

data class CreateFormLoanDTO(
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

data class FormLoanDto(
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