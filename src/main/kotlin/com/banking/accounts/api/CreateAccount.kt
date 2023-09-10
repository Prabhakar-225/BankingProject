package com.banking.accounts.api

import com.banking.accounts.query.CustomerAddress
import org.axonframework.modelling.command.TargetAggregateIdentifier
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class CreateAccountCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null,
    var bankId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? = null
)

data class AccountCreatedEvent(
    var accountId: String? = null,
    var bankId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? =null
)


data class CreateAccountDTO(
    var bankName: String? = null,
    var bankId: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    @get:Size(min = 8, max = 20, message = "accountNumber should be between min-3 to max-80 Alphabets")
    @get:NotEmpty(message = "accountNumber should not be empty")
    @get:NotNull(message = "accountNumber should not be Null")
    @get:NotBlank(message = "accountNumber should not be Blank")
    var accountNumber: Long? = 0L,
    @get:Size(min = 3, max = 80, message = "customerName should be between min-3 to max-80 Alphabets")
    @get:NotEmpty(message = "customerName should not be empty")
    @get:NotNull(message = "customerName should not be Null")
    @get:NotBlank(message = "customerName should not be Blank")
    var customerName: String? = null,
    @get:NotEmpty(message = "customerAddress should not be empty ")
    @get:NotNull(message = "customerAddress should not be Null ")
    @get:NotBlank(message = "customerAddress should not be Blank")
    var customerAddress: CustomerAddress? = null,

    var deleted: Boolean? = null,
    var balance: Double? = 0.0,
    var branchLocation: String? = null

)


data class AccountDto(
    var accountId: String? = null,
    var bankId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var accountNumber: Long? = 0L,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? =null
)