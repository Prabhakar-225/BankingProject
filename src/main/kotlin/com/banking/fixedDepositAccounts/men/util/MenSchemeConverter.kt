package com.banking.fixedDepositAccounts.men.util

import com.banking.fixedDepositAccounts.men.api.*
import com.banking.fixedDepositAccounts.men.query.MenSchemes
import java.util.*

object MenSchemeConverter {

    fun convertModelToDTO(state: MenSchemes): MenSchemeDto {
        return MenSchemeDto(state.menSchemeId,state.schemeName,
            state.purposeOfScheme, state.accountNumber,state.nameOfPerson,
            state.phoneNumber, state.address,state.bankName,
            state.branchLocation, state.ifscCode,state.depositBalance,
            state.bankId,state.deleted)
    }

    fun convertDtoToCommand(createDTO: CreateMenSchemeDTO): CreateMenSchemeCommand {
        return CreateMenSchemeCommand(UUID.randomUUID().toString(),createDTO.schemeName,
            createDTO.purposeOfScheme, createDTO.accountNumber,createDTO.nameOfPerson,
            createDTO.phoneNumber, createDTO.address,createDTO.bankName,
            createDTO.branchLocation, createDTO.ifscCode,createDTO.depositBalance,
            createDTO.bankId,createDTO.deleted)

    }


    fun convertDtoToCommandToUpdateAccount(updateDTO: UpdateMenSchemeDTO): UpdateMenSchemeCommand {
        return UpdateMenSchemeCommand(updateDTO.menSchemeId,updateDTO.schemeName,
            updateDTO.purposeOfScheme,updateDTO.nameOfPerson,
            updateDTO.phoneNumber, updateDTO.address,updateDTO.bankName,
            updateDTO.branchLocation, updateDTO.ifscCode,updateDTO.depositBalance,
            updateDTO.bankId,updateDTO.deleted)
    }

    fun convertDtoToCommandToAccountDelete(menSchemeId: String): DeleteMenSchemeCommand {
        return DeleteMenSchemeCommand(menSchemeId)
    }

    fun convertDtoToCommandToAccountDeletePermanently(menSchemeId: String): PermanentDeleteMenSchemeCommand {
        return PermanentDeleteMenSchemeCommand(menSchemeId)
    }

    fun convertDtoToCommandToAccountRestore(menSchemeId: String): RestoreDeletedMenSchemeCommand {
        return RestoreDeletedMenSchemeCommand(menSchemeId)
    }
}