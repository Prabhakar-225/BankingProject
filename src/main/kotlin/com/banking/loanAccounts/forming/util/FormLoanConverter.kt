package com.banking.loanAccounts.forming.util

import com.banking.loanAccounts.forming.api.*
import com.banking.loanAccounts.forming.query.FormingLoan
import java.util.*

object FormLoanConverter {

    fun convertModelToDTO(state: FormingLoan): FormLoanDto {
        return FormLoanDto(
            state.loanId, state.consumerName, state.consumerNumber,
            state.consumerAddress, state.identityDetails, state.landDetails,
            state.bankName, state.accountNumber, state.ifscCode, state.branchLocation,
            state.sanctionedAmount, state.loanInterest, state.bankId, state.deleted
        )
    }

    fun convertDtoToCommandToCreate(createDTO: CreateFormLoanDTO): CreateFormLoanCommand {
        return CreateFormLoanCommand(
            UUID.randomUUID().toString(), createDTO.consumerName, createDTO.consumerNumber,
            createDTO.consumerAddress, createDTO.identityDetails, createDTO.landDetails,
            createDTO.bankName, createDTO.accountNumber, createDTO.ifscCode, createDTO.branchLocation,
            createDTO.sanctionedAmount, createDTO.loanInterest, createDTO.bankId, createDTO.deleted
        )

    }

    fun convertDtoToCommandToUpdateAccount(updateDTO: UpdateFormLoanDTO): UpdateFormLoanCommand {
        return UpdateFormLoanCommand(
            updateDTO.loanId, updateDTO.consumerName, updateDTO.consumerNumber,
            updateDTO.consumerAddress, updateDTO.identityDetails, updateDTO.landDetails,
            updateDTO.bankName, updateDTO.ifscCode, updateDTO.branchLocation,
            updateDTO.sanctionedAmount, updateDTO.loanInterest, updateDTO.bankId, updateDTO.deleted
        )
    }

    fun convertDtoToCommandToAccountDelete(loanId: String): DeleteFormLoanCommand {
        return DeleteFormLoanCommand(loanId)
    }

    fun convertDtoToCommandToAccountDeletePermanent(loanId: String): PermanentDeleteFormLoanCommand {
        return PermanentDeleteFormLoanCommand(loanId)
    }

    fun convertDtoToCommandToAccountRestore(loanId: String): RestoreDeletedFormLoanCommand {
        return RestoreDeletedFormLoanCommand(loanId)
    }
}