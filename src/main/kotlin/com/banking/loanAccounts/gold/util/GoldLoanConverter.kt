package com.banking.loanAccounts.gold.util

import com.banking.loanAccounts.gold.api.*
import com.banking.loanAccounts.gold.query.GoldLoan
import java.util.UUID

object GoldLoanConverter {

    fun convertModelToDTO(state: GoldLoan): GoldLoanAccountDto{
        return GoldLoanAccountDto(state.loanId,state.consumerName,state.consumerNumber,
            state.consumerAddress,state.identityDetails,state.goldGrams,state.bankName,state.accountNumber,
            state.ifscCode,state.branchLocation,state.sanctionedAmount,state.loanInterest,
            state.bankId,state.deleted)
    }

    fun convertDtoToCommandToCreate(create: CreateGoldLoanAccountDTO): CreateGoldLoanAccountCommand{
        return CreateGoldLoanAccountCommand(UUID.randomUUID().toString(),create.consumerName,create.consumerNumber,
            create.consumerAddress,create.identityDetails,create.goldGrams,create.bankName,
            create.accountNumber,create.ifscCode,create.branchLocation,create.sanctionedAmount,
            create.loanInterest,create.bankId,create.deleted)
    }

    fun convertDtoToCommandToUpdate(update: UpdateGoldLoanAccountDTO): UpdateGoldLoanAccountCommand{
        return UpdateGoldLoanAccountCommand(update.loanId,update.consumerName,update.consumerNumber,
            update.consumerAddress,update.identityDetails,update.goldGrams,update.bankName,
            update.ifscCode,update.branchLocation,update.sanctionedAmount,update.loanInterest,
            update.bankId,update.deleted)
    }

    fun convertDtoToCommandToDeleteAccount(loanId: String): DeleteGoldLoanAccountCommand{
        return DeleteGoldLoanAccountCommand(loanId)
    }

    fun convertDtoToCommandToRestoreAccount(loanId: String): RestoreDeletedGoldLoanAccountCommand{
        return RestoreDeletedGoldLoanAccountCommand(loanId)
    }

    fun convertDtoToCommandToDeleteAccountPermanent(loanId: String): PermanentDeleteGoldLoanAccountCommand{
        return PermanentDeleteGoldLoanAccountCommand(loanId)
    }

}