package com.banking.loanAccounts.house.util

import com.banking.loanAccounts.house.api.*
import com.banking.loanAccounts.house.query.HouseLoan
import java.util.UUID

object HouseLoanConverter {

    fun convertModelToDTO(state: HouseLoan): HouseLoanDto{
        return HouseLoanDto(state.loanId,state.consumerName,state.consumerNumber,state.consumerAddress,
            state.identityDetails,state.houseDetails,state.houseCents,state.bankName,state.accountNumber,
            state.ifscCode,state.branchLocation,state.sanctionedAmount,state.loanInterest,state.bankId,
            state.deleted)
    }

    fun convertDtoToCommandToCreate(create: CreateHouseLoanDTO): CreateHouseLoanCommand{
        return CreateHouseLoanCommand(UUID.randomUUID().toString(),create.consumerName,create.consumerNumber,
            create.consumerAddress,create.identityDetails,create.houseDetails,create.houseCents,
            create.bankName,create.accountNumber,create.ifscCode,create.branchLocation,create.sanctionedAmount,
            create.loanInterest,create.bankId,create.deleted)
    }

    fun convertDtoToCommandToUpdate(update: UpdateHouseLoanDTO): UpdateHouseLoanCommand{
        return UpdateHouseLoanCommand(update.loanId,update.consumerName,update.consumerNumber,
            update.consumerAddress,update.identityDetails,update.houseDetails,update.houseCents,
            update.bankName,update.ifscCode,update.branchLocation,update.sanctionedAmount,
            update.loanInterest,update.bankId,update.deleted)
    }

    fun convertDtoToCommandToDelete(loanId: String): DeleteHouseLoanCommand{
        return DeleteHouseLoanCommand(loanId)
    }

    fun convertDtoToCommandToRestore(loanId: String): RestoreDeletedHouseLoanCommand{
        return RestoreDeletedHouseLoanCommand(loanId)
    }

    fun convertDtoToCommandToPermanentDelete(loanId: String): PermanentDeleteHouseLoanCommand{
        return PermanentDeleteHouseLoanCommand(loanId)
    }
}