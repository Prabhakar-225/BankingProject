package com.banking.loanAccounts.personal.api

import com.banking.fixedDepositAccounts.girlsScheme.query.Address
import com.banking.loanAccounts.forming.query.IdentityDetails
import com.banking.loanAccounts.personal.query.Qualification
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreatePersonalLoanCommand(
    @TargetAggregateIdentifier
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

data class PersonalLoanCreatedEvent(
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

data class CreatePersonalLoanDTO(
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

data class PersonalLoanDto(
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