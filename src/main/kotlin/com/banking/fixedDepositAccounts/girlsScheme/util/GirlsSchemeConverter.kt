package com.banking.fixedDepositAccounts.girlsScheme.util

import com.banking.fixedDepositAccounts.girlsScheme.api.*
import com.banking.fixedDepositAccounts.girlsScheme.query.GirlsScheme
import java.util.*

object GirlsSchemeConverter {

    fun convertModelToDTO(state: GirlsScheme): GirlsSchemeDto {
        return GirlsSchemeDto(state.girlsSchemeId,state.bankName, state.accountType,state.schemeName,
            state.accountNumber,state.girlName,state.depositBalance,state.branchLocation,state.ifscCode,
            state.address,state.deleted,state.bankId)
    }

    fun convertDtoToCommand(createDTO: CreateGirlsSchemeDTO): CreateGirlsSchemeCommand {
        return CreateGirlsSchemeCommand(
            UUID.randomUUID().toString(), createDTO.bankName,createDTO.accountType,createDTO.schemeName,
            createDTO.accountNumber,createDTO.girlName,createDTO.depositBalance, createDTO.branchLocation,
            createDTO.ifscCode,createDTO.address,createDTO.deleted,createDTO.bankId)

    }

    fun convertDtoToCommandToUpdateAccount(updateDTO: UpdateGirlsSchemeDTO): UpdateGirlsSchemeCommand {
        return UpdateGirlsSchemeCommand( updateDTO.girlsSchemeId,updateDTO.bankName,updateDTO.accountType,
            updateDTO.schemeName,updateDTO.girlName,updateDTO.depositBalance, updateDTO.branchLocation,
            updateDTO.ifscCode,updateDTO.address,updateDTO.deleted,updateDTO.bankId)
    }

    fun convertDtoToCommandToAccountDelete(girlsSchemeId: String): DeleteGirlsSchemeCommand {
        return DeleteGirlsSchemeCommand(girlsSchemeId )
    }

    fun convertDtoToCommandToAccountDeletePermanently(girlsSchemeId: String):DeleteGirlsSchemePermanentCommand{
        return DeleteGirlsSchemePermanentCommand(girlsSchemeId)
    }

    fun convertDtoToCommandToAccountRestore(girlsSchemeId: String): RestoreGirlsSchemeAccountCommand {
        return RestoreGirlsSchemeAccountCommand(girlsSchemeId)
    }
}