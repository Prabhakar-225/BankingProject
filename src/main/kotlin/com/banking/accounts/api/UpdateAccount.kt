package com.banking.accounts.api

import com.banking.accounts.query.CustomerAddress
import org.axonframework.modelling.command.TargetAggregateIdentifier
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateAccountCommand(
    @TargetAggregateIdentifier
    var accountId: String? = null,
    var bankId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? = null
)

data class AccountUpdatedEvent(
    var accountId: String? = null,
    var bankId: String? = null,
    var bankName: String? = null,
    var bankBranchLocation: String? = null,
    var ifscCode: String? = null,
    var accountType: String? = null,
    var customerName: String? = null,
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = null,
    var deleted: Boolean? = null
)


data class UpdateAccountDto(
    var bankId: String? = null,
    var accountId: String? = null,
    @get:NotEmpty(message = "bankName should not be empty")
    @get:NotNull(message = "bankName should not be Null")
    @get:NotBlank(message = "bankName should not be Blank")
    var bankName: String? = null,
    @get:NotEmpty(message = "branchLocation should not be empty")
    @get:NotNull(message = "branchLocation should not be Null")
    @get:NotBlank(message = "branchLocation should not be Blank")
    var bankBranchLocation: String? = null,
    @get:NotEmpty(message = "ifscCode should not be empty")
    @get:NotNull(message = "ifscCode should not be Null")
    @get:NotBlank(message = "ifscCode should not be Blank")
    var ifscCode: String? = null,

    @get:NotEmpty(message = "accountType should not be empty")
    @get:NotNull(message = "accountType should not be Null")
    @get:NotBlank(message = "accountType should not be Blank")
    var accountType: String? = null,

    @get:Size(min = 3, max = 80, message = "customerName should be between min-3 to max-80 Alphabets")
    @get:NotEmpty(message = "customerName should not be empty")
    @get:NotNull(message = "customerName should not be Null")
    @get:NotBlank(message = "customerName should not be Blank")
    var customerName: String? = null,
    @get:NotEmpty(message = "deleted should not be empty give true for deletion false for present")
    @get:NotNull(message = "deleted should not be Null give true for deletion false for present")
    @get:NotBlank(message = "deleted should not be Blank give true for deletion false for present")
    var deleted: Boolean? = null,
    @get:NotEmpty(message = "customerAddress should not be empty")
    @get:NotNull(message = "customerAddress should not be Null")
    @get:NotBlank(message = "customerAddress should not be Blank")
    var customerAddress: CustomerAddress? = null,
    var balance: Double? = 0.0

)