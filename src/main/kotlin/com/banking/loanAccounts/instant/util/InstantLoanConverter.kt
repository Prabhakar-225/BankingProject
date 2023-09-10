package com.banking.loanAccounts.instant.util


import com.banking.loanAccounts.instant.api.*
import com.banking.loanAccounts.instant.query.InstantLoan
import java.util.UUID

object InstantLoanConverter {

    fun convertModelToDto(state: InstantLoan): InstantLoanDto{
        return InstantLoanDto(state.loanId,state.consumerName,state.consumerNumber,
            state.personAddress,state.personalDetails,state.bankName,state.accountNumber,
            state.ifscCode,state.branchLocation,state.sanctionedAmount,state.loanInterest,
            state.bankId,state.deleted)
    }


    fun convertDtoToCommandToCreate(create: CreateInstantLoanDTO): CreateInstantLoanCommand{
        return CreateInstantLoanCommand(UUID.randomUUID().toString(),create.consumerName,create.consumerNumber,
            create.personAddress,create.personalDetails,create.bankName,create.accountNumber,
            create.ifscCode,create.branchLocation,create.sanctionedAmount,create.loanInterest,
            create.bankId,create.deleted)
    }


    fun convertDtoToCommandToUpdate(update: UpdateInstantLoanDTO): UpdateInstantLoanCommand{
        return UpdateInstantLoanCommand(update.loanId,update.consumerName,update.consumerNumber,
            update.personAddress,update.personalDetails,update.bankName, update.ifscCode,
            update.branchLocation,update.sanctionedAmount,update.loanInterest,
            update.bankId,update.deleted)
    }

    fun convertDtoToCommandToDelete(loanId: String): DeleteInstantLoanCommand{
        return DeleteInstantLoanCommand(loanId)
    }

    fun convertDtoToCommandToRestore(loanId: String): RestoreDeletedInstantLoanCommand{
        return RestoreDeletedInstantLoanCommand(loanId)
    }

    fun convertDtoToCommandToDeletePermanent(loanId: String): PermanentDeleteInstantLoanCommand{
        return PermanentDeleteInstantLoanCommand(loanId)
    }

}