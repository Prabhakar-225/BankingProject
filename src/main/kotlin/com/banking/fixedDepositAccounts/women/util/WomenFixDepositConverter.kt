package com.banking.fixedDepositAccounts.women.util

import com.banking.fixedDepositAccounts.women.api.*
import com.banking.fixedDepositAccounts.women.query.WomenFixDeposit
import java.util.*

object WomenFixDepositConverter {

    fun convertModelToDTO(state: WomenFixDeposit): WomenFixedDepositDto {
        return WomenFixedDepositDto(state.accountId,state.bankName, state.accountType,state.accountNumber,
            state.customerName,state.depositBalance,state.branchLocation,state.ifscCode,
            state.customerAddress,state.deleted)
    }

    fun convertDtoToCommand(createDTO: CreateWomenFixDepositDTO): CreateWomenFixDepositCommand {
        return CreateWomenFixDepositCommand(
            UUID.randomUUID().toString(), createDTO.bankName,createDTO.accountType, createDTO.accountNumber,
            createDTO.customerName,createDTO.depositBalance, createDTO.branchLocation,
            createDTO.ifscCode,createDTO.customerAddress,createDTO.deleted)

    }

    fun convertDtoToCommandToUpdateAccount(updateDTO: UpdateWomenFixDepositDTO): UpdateWomenFixDepositCommand {
        return UpdateWomenFixDepositCommand( updateDTO.accountId,updateDTO.bankName,updateDTO.accountType,
            updateDTO.customerName,updateDTO.depositBalance, updateDTO.branchLocation,
            updateDTO.ifscCode,updateDTO.customerAddress,updateDTO.deleted)
    }

    fun convertDtoToCommandToAccountDelete(accountId: String): DeleteWomenFixDepositCommand {
        return DeleteWomenFixDepositCommand(accountId)
    }

    fun convertDtoToCommandToAccountDeletePermanent(accountId: String): DeletePermanentWomenFixDepositCommand{
        return DeletePermanentWomenFixDepositCommand(accountId)
    }

    fun convertDtoToCommandToAccountRestore(accountId: String): RestoreWomenFixDepositCommand{
        return RestoreWomenFixDepositCommand(accountId)
    }
}