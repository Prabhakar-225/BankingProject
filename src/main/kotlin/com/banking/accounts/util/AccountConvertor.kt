package com.banking.accounts.util

import com.banking.accounts.api.*
import com.banking.accounts.query.AccountState
import java.util.*

object AccountConvertor {

    fun convertModelToDTO(state: AccountState): AccountDto {
        return AccountDto(state.accountId,state.bankId,state.bankName,state.bankBranchLocation,
            state.ifscCode, state.accountType,state.accountNumber,state.customerName,
            state.customerAddress, state.balance,state.deleted)
    }

    fun convertDtoToCommand(createAccountDTO: CreateAccountDTO): CreateAccountCommand {
        return CreateAccountCommand(UUID.randomUUID().toString(),createAccountDTO.bankId,
            createAccountDTO.bankName, createAccountDTO.bankBranchLocation,createAccountDTO.ifscCode,
            createAccountDTO.accountType,createAccountDTO.accountNumber,createAccountDTO.customerName,
            createAccountDTO.customerAddress, createAccountDTO.balance,createAccountDTO.deleted)

    }

    fun convertDtoToCommandToUpdateAccount(updateAccountDto: UpdateAccountDto): UpdateAccountCommand {
        return UpdateAccountCommand(updateAccountDto.accountId,updateAccountDto.bankId,
            updateAccountDto.bankName, updateAccountDto.bankBranchLocation,updateAccountDto.ifscCode,
            updateAccountDto.accountType,updateAccountDto.customerName,
            updateAccountDto.customerAddress, updateAccountDto.balance,updateAccountDto.deleted)
    }

    fun convertDtoToCommandToAccountDelete(accountId: String): DeleteAccountCommand {
        return DeleteAccountCommand(accountId)
    }

    fun convertDtoToCommandToDepositAmount(updateAmountDto: UpdateAmountDto): DepositMoneyCommand{
        return DepositMoneyCommand(updateAmountDto.accountId,updateAmountDto.amount)
    }

    fun convertDtoToCommandToWithdrawAmount(updateAmountDto: UpdateAmountDto): WithdrawMoneyCommand{
        return WithdrawMoneyCommand(updateAmountDto.accountId,updateAmountDto.amount)
    }

    fun convertDtoToCommandToTransactionAmount(updateTransaction: UpdateTransactionAmountDto): MoneyTransactionCommand{
        return MoneyTransactionCommand(updateTransaction.accountIdSends,updateTransaction.sendAmount,
            updateTransaction.accountIdReceived)
    }

    fun convertModelToDtoAccount(account: List<AccountState>): List<AccountDto> {
        return account.map { x -> convertModelToDTO(x) }
    }

}