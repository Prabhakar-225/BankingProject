package com.banking.loanAccounts.forming.controller

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.forming.api.CreateFormLoanDTO
import com.banking.loanAccounts.forming.api.FormLoanDto
import com.banking.loanAccounts.forming.api.UpdateFormLoanDTO
import com.banking.loanAccounts.forming.service.FormingLoanService
import com.banking.loanAccounts.forming.util.FormLoanConverter
import org.springframework.beans.factory.annotation.Autowired
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
@RequestMapping("/formingLoans")
class FormLoanController {

    @Autowired
    private var service: FormingLoanService? = null

    @PostMapping("/createFormLoanAccount")
    fun createAccount(@RequestBody @Valid createDTO: CreateFormLoanDTO): CompletableFuture<ResponseWithError<String>>{
        val result = FormLoanConverter.convertDtoToCommandToCreate(createDTO)
        return this.service!!.createFormLoan(result)
    }

    @PutMapping("/updateFormLoanAccount")
    fun updateAccount(@RequestBody @Valid updateDTO: UpdateFormLoanDTO): CompletableFuture<ResponseWithError<String>>{
        val result = FormLoanConverter.convertDtoToCommandToUpdateAccount(updateDTO)
        return this.service!!.updateFormLoan(result)
    }

    @GetMapping("/findAllLoanAccounts")
    fun findAllAccounts():CompletableFuture<ResponseWithError<List<FormLoanDto>>>{
        return this.service!!.findAllLoanAccounts()
    }

    @GetMapping("/findAccountByLoanId/{loanId}")
    fun findAccountByLoanId(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<FormLoanDto>>{
        return this.service!!.findAccountByLoanId(loanId)
    }

    @PutMapping("/deleteAccountById/{loanId}")
    fun deleteAccountTemporary(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = FormLoanConverter.convertDtoToCommandToAccountDelete(loanId)
        return this.service!!.deleteAccount(result)
    }

    @PutMapping("/restoreDeletedAccount/{loanId}")
    fun restoreDeletedAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = FormLoanConverter.convertDtoToCommandToAccountRestore(loanId)
        return this.service!!.restoreDeletedAccount(result)
    }

    @DeleteMapping("/deleteAccountPermanently/{loanId}")
    fun deleteAccountPermanently(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = FormLoanConverter.convertDtoToCommandToAccountDeletePermanent(loanId)
        return this.service!!.permanentDeleteAccount(result)
    }

}