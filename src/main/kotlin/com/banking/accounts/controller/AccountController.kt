package com.banking.accounts.controller

import com.banking.accounts.api.*
import com.banking.accounts.service.AccountService
import com.banking.accounts.util.AccountConvertor
import com.banking.bank.api.AddAccountsToBankDTO
import com.banking.bank.service.BankService
import com.banking.bank.util.BankConverter
import com.banking.configurations.ResponseWithError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/accounts")
class AccountController {

    @Autowired
    private val accountService: AccountService? = null

    @Autowired
    private val bankService:BankService? = null

    //create account
    @PostMapping("/createAccount")
    fun createAccount(@RequestBody @Valid createAccountDTO: CreateAccountDTO): CompletableFuture<ResponseEntity<String>>{
        val result=AccountConvertor.convertDtoToCommand(createAccountDTO)
        return accountService!!.createAccount(result)
    }

    //Update record by accountId
    @PutMapping("/updateAccount")
    fun updateAccount(@RequestBody @Valid updateDto: UpdateAccountDto): CompletableFuture<ResponseEntity<String>>{
        val  result= AccountConvertor.convertDtoToCommandToUpdateAccount(updateDto)
        return accountService!!.updateAccount(result)
    }

    //find all accounts
    @GetMapping("/findAllAccounts")
    fun  findAllAccounts(): CompletableFuture<ResponseWithError<List<AccountDto>>>{
        return  this.accountService!!.findAllAccounts()
    }

    // find all records which is balance greater than 20000
    @GetMapping("/findAllRecordsByBalance")
    fun findAllRecordsByBalance(): CompletableFuture<ResponseWithError<List<AccountDto>>>{
        return  this.accountService!!.findAllRecordsByBalance()
    }

//    @GetMapping("/deletedRecords")
//    fun findDeletedRecords(): CompletableFuture<ResponseWithError<List<AccountDto>>>{
//        return  this.accountService!!.findDeletedRecords()
//    }

    //find account by accountId
    @GetMapping("/findByAccountId/{accountId}")
    fun findById(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<AccountDto>> {
        return this.accountService!!.findByAccountId(accountId)
    }



    // Soft core Deletion
    @PutMapping("/deleteByAccountId/{accountId}")
    fun deleteAccountById(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<String>> {
        val result = AccountConvertor.convertDtoToCommandToAccountDelete(accountId)
        return accountService!!.deleteAccountByIdTemporary(result)
    }

    //Hard core Deletion
    @DeleteMapping("deleteAccountPermanentlyBy/{accountId}")
    fun deleteAccountByIdPermanent(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<String>> {
        val result = AccountConvertor.convertDtoToCommandToAccountDelete(accountId)
        return accountService!!.deleteAccountByIdPermanent(result)
    }

    //Amount DepositOperation
    @PutMapping("/depositAmount")
    fun depositAmount(@RequestBody @Valid updateAmountDto: UpdateAmountDto): CompletableFuture<ResponseWithError<String>>{
        val result=AccountConvertor.convertDtoToCommandToDepositAmount(updateAmountDto)
        return  accountService!!.depositAmount(result)
    }

    //Amount WithdrawOperation
    @PutMapping("/withdrawAmount")
    fun withdrawAmount(@RequestBody @Valid updateAmountDto: UpdateAmountDto): CompletableFuture<ResponseWithError<String>>{
        val result= AccountConvertor.convertDtoToCommandToWithdrawAmount(updateAmountDto)
        return  accountService!!.withdrawBalance(result)
    }

    // transaction via id to id
    @PutMapping("/transactionsViaIds")
    fun transactionalAmount(@RequestBody @Valid updateTransactionAmountDto: UpdateTransactionAmountDto): CompletableFuture<ResponseWithError<String>>{
        val result= AccountConvertor.convertDtoToCommandToTransactionAmount(updateTransactionAmountDto)
        return  accountService!!.amountTransactions(result)
    }

    @PostMapping("/addAccount")
    fun addAccount(@RequestBody  addAccountDto: AddAccountsToBankDTO): CompletableFuture<ResponseWithError<String>> {
        val result= BankConverter.convertDtoToAddCommand(addAccountDto)
        return bankService!!.addAccount(result)
    }

}