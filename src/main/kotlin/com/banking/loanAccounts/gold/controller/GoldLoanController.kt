package com.banking.loanAccounts.gold.controller

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.gold.api.*
import com.banking.loanAccounts.gold.service.GoldLoanService
import com.banking.loanAccounts.gold.util.GoldLoanConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Rsocket
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/goldLoans")
class GoldLoanController {

    @Autowired
    private val service: GoldLoanService? = null

    @PostMapping("/CreateAccount")
    fun createGoldLoanAccount(@RequestBody @Valid createDTO: CreateGoldLoanAccountDTO): CompletableFuture<ResponseWithError<String>>{
        val result = GoldLoanConverter.convertDtoToCommandToCreate(createDTO)
        return this.service!!.createAccount(result)
    }

    @PutMapping("/updateAccount")
    fun updateGoldLoanAccount(@RequestBody @Valid updateDTO: UpdateGoldLoanAccountDTO): CompletableFuture<ResponseWithError<String>>{
        val result = GoldLoanConverter.convertDtoToCommandToUpdate(updateDTO)
        return this.service!!.updateAccount(result)
    }

    @GetMapping("/findAllAccounts")
    fun findAll(): CompletableFuture<ResponseWithError<List<GoldLoanAccountDto>>>{
        return this.service!!.findAllAccounts()
    }

    @GetMapping("/findByLoanId/{loanId}")
    fun findAccountById(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<GoldLoanAccountDto>>{
        return this.service!!.findAccountByLoanId(loanId)
    }

    @PutMapping("/temporaryDeletion/{loanId}")
    fun temporaryDeletion(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = GoldLoanConverter.convertDtoToCommandToDeleteAccount(loanId)
        return this.service!!.deleteAccount(result)
    }

    @PutMapping("/restoreAccountById/{loanId}")
    fun restoreDeletedAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = GoldLoanConverter.convertDtoToCommandToRestoreAccount(loanId)
        return this.service!!.restoreDeletedAccount(result)
    }

    @DeleteMapping("/permanentDeletion/{loanId}")
    fun deletePermanently(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = GoldLoanConverter.convertDtoToCommandToDeleteAccountPermanent(loanId)
        return this.service!!.accountDeletePermanently(result)
    }
}