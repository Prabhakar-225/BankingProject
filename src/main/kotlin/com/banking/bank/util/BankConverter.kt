package com.banking.bank.util


import com.banking.bank.api.*
import com.banking.bank.query.Accounts
import com.banking.bank.query.BankState
import java.util.*

object BankConverter {

    fun convertModelToDTO(state: BankState): BankDto {
        return BankDto(
            state.bankId, state.bankName, state.accountDetails?.map { x ->
                Accounts(
                    x.accountId, x.accountType, x.accountNumber,
                    x.customerName, x.balance, x.branchLocation, x.deleted
                )
            }?.toList(),
            state.bankLocation, state.ifscCode, state.accounts, state.bankHeadOffice, state.bankDeleted
        )
    }

    fun convertDtoToCommand(createBankDTO: CreateBankDTO): CreateBankCommand {
        return CreateBankCommand(
            UUID.randomUUID().toString(), createBankDTO.bankName,
            createBankDTO.accountDetails?.map { x ->
                Accounts(
                    x.accountId, x.accountType, x.accountNumber,
                    x.customerName, x.balance, x.branchLocation, x.deleted
                )
            }?.toList(), createBankDTO.bankLocation, createBankDTO.ifscCode,
            createBankDTO.accounts, createBankDTO.bankHeadOffice, createBankDTO.bankDeleted
        )

    }

    fun convertDtoToCommandToUpdateBank(updateBankDTO: UpdateBankDto): UpdateBankCommand {
        return UpdateBankCommand(
            updateBankDTO.bankId, updateBankDTO.bankName,
            updateBankDTO.accountDetails?.map { x ->
                Accounts(
                    x.accountId, x.accountType, x.accountNumber,
                    x.customerName, x.balance, x.branchLocation, x.deleted
                )
            }?.toList(), updateBankDTO.bankLocation, updateBankDTO.ifscCode,
            updateBankDTO.accounts, updateBankDTO.bankHeadOffice, updateBankDTO.bankDeleted
        )
    }

    fun convertDtoToCommandToBankDelete(bankId: String): DeleteBankCommand {
        return DeleteBankCommand(bankId)
    }

    fun convertDtoToCommandToBankPermanentDelete(bankId: String): PermanentDeleteBankCommand {
        return PermanentDeleteBankCommand(bankId)
    }

    fun convertDtoToAddCommand(dto: AddAccountsToBankDTO): AddAccountsToBankCommand {
        return AddAccountsToBankCommand(
            dto.bankId, UUID.randomUUID().toString(), dto.bankName, dto.bankBranchLocation,
            dto.ifscCode, dto.accountType, dto.accountNumber, dto.customerName,
            dto.customerAddress, dto.balance, false
        )

    }
}