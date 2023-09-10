package com.banking.loanAccounts.personal.util

import com.banking.loanAccounts.personal.api.*
import com.banking.loanAccounts.personal.query.PersonalLoan
import java.util.UUID

object PersonalLoanConverter {

    fun convertModelToDto(state: PersonalLoan):PersonalLoanDto{
        return PersonalLoanDto(state.loanId,state.personName,state.personNumber,state.personAddress,
            state.personalDetails,state.qualificationDetails,state.bankName,state.accountNumber,
            state.ifscCode,state.branchLocation,state.grantedAmount,state.loanInterest,
            state.loanTimePeriod, state.bankId,state.deleted)
    }

    fun convertDtoToCommandToCreate(create: CreatePersonalLoanDTO): CreatePersonalLoanCommand{
        return CreatePersonalLoanCommand(UUID.randomUUID().toString(),create.personName,create.personNumber,
            create.personAddress,create.personalDetails,create.qualificationDetails,create.bankName,
            create.accountNumber,create.ifscCode,create.branchLocation,create.grantedAmount,create.loanInterest,
            create.loanTimePeriod,create.bankId,create.deleted)
    }

    fun convertDtoToCommandToUpdate(update: UpdatePersonalLoanDTO): UpdatePersonalLoanCommand{
        return UpdatePersonalLoanCommand(update.loanId,update.personName,update.personNumber,
            update.personAddress,update.personalDetails,update.qualificationDetails,update.bankName,
            update.ifscCode,update.branchLocation,update.grantedAmount,update.loanInterest,
            update.loanTimePeriod,update.bankId,update.deleted)
    }

    fun convertDtoToCommandToDelete(loanId: String): DeletePersonalLoanCommand{
        return DeletePersonalLoanCommand(loanId)
    }

    fun convertDtoToCommandToRestore(loanId: String): RestorePersonalLoanCommand{
        return RestorePersonalLoanCommand(loanId)
    }

    fun convertDtoToCommandToPermanentDelete(loanId: String): PermanentDeletePersonalLoanCommand{
        return PermanentDeletePersonalLoanCommand(loanId)
    }
}