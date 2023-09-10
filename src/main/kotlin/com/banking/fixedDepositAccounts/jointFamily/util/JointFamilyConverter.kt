package com.banking.fixedDepositAccounts.jointFamily.util

import com.banking.fixedDepositAccounts.jointFamily.api.*
import com.banking.fixedDepositAccounts.jointFamily.query.JointFamily
import java.util.*

object JointFamilyConverter {

    fun convertModelToDTO(state: JointFamily): JointFamilyDto {
        return JointFamilyDto(state.jointFamilyId,state.accountNumber, state.schemeName,
            state.jointFamilyName,state.jointFamilyAddress,state.personsToInvest,
            state.personsNames,state.personsPhoneNumbers,state.bankName,state.branchLocation,
            state.ifscCode, state.depositMoney,state.bankId,state.deleted)
    }

    fun convertDtoToCommand(createDTO: CreateJointFamilyDTO): CreateJointFamilyCommand {
        return CreateJointFamilyCommand(
            UUID.randomUUID().toString(),createDTO.accountNumber, createDTO.schemeName,
            createDTO.jointFamilyName,createDTO.jointFamilyAddress,createDTO.personsToInvest,
            createDTO.personsNames,createDTO.personsPhoneNumbers,createDTO.bankName,createDTO.branchLocation,
            createDTO.ifscCode, createDTO.depositMoney,createDTO.bankId,createDTO.deleted)

    }

    
    fun convertDtoToCommandToUpdateAccount(updateDTO: UpdateJointFamilyDTO): UpdateJointFamilyCommand {
        return UpdateJointFamilyCommand(updateDTO.jointFamilyId, updateDTO.schemeName,
            updateDTO.jointFamilyName,updateDTO.jointFamilyAddress,updateDTO.personsToInvest,
            updateDTO.personsNames,updateDTO.personsPhoneNumbers,updateDTO.bankName,updateDTO.branchLocation,
            updateDTO.ifscCode, updateDTO.depositMoney,updateDTO.bankId,updateDTO.deleted)
    }

    fun convertDtoToCommandToAccountDelete(jointFamilyId: String): DeleteJointFamilyCommand {
        return DeleteJointFamilyCommand(jointFamilyId )
    }

    fun convertDtoToCommandToAccountDeletePermanently(jointFamilyId: String): DeletePermanentJointFamilyCommand {
        return DeletePermanentJointFamilyCommand(jointFamilyId)
    }

    fun convertDtoToCommandToAccountRestore(jointFamilyId: String): RestoreJointFamilyCommand {
        return RestoreJointFamilyCommand(jointFamilyId)
    }
}