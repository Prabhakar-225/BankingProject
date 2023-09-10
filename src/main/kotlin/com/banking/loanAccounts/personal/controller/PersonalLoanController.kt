package com.banking.loanAccounts.personal.controller

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.personal.api.CreatePersonalLoanDTO
import com.banking.loanAccounts.personal.api.PersonalLoanDto
import com.banking.loanAccounts.personal.api.UpdatePersonalLoanDTO
import com.banking.loanAccounts.personal.service.PersonalLoanService
import com.banking.loanAccounts.personal.util.PersonalLoanConverter
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
@RequestMapping("/personalLoans")
class PersonalLoanController {

    @Autowired
    private val service: PersonalLoanService? = null

    @PostMapping("/createPersonalLoan")
    fun createLoan(@RequestBody @Valid createDTO: CreatePersonalLoanDTO): CompletableFuture<ResponseWithError<String>>{
        val result = PersonalLoanConverter.convertDtoToCommandToCreate(createDTO)
        return this.service!!.createLoan(result)
    }

    @PutMapping("/updatePersonalLoan")
    fun updateLoan(@RequestBody @Valid updateDTO: UpdatePersonalLoanDTO): CompletableFuture<ResponseWithError<String>> {
        val result = PersonalLoanConverter.convertDtoToCommandToUpdate(updateDTO)
        return this.service!!.updateLoan(result)
    }

    @GetMapping("/findAllLoanAccounts")
    fun findAllAccounts(): CompletableFuture<ResponseWithError<List<PersonalLoanDto>>>{
        return this.service!!.findAllAccounts()
    }

    @GetMapping("/findPersonalLoanBy/{loanId}")
    fun findAccountById(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<PersonalLoanDto>>{
        return this.service!!.findAccountByLoanId(loanId)
    }

    @PutMapping("/temporaryDelete/{loanId}")
    fun temporaryDeletion(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = PersonalLoanConverter.convertDtoToCommandToDelete(loanId)
        return this.service!!.temporaryDeletion(result)
    }

    @PutMapping("/restoreAccountBy/{loanId}")
    fun restoreDeletedAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = PersonalLoanConverter.convertDtoToCommandToRestore(loanId)
        return this.service!!.restoreAccount(result)
    }

    @DeleteMapping("/permanentDeletionBy/{loanId}")
    fun permanentDeletion(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = PersonalLoanConverter.convertDtoToCommandToPermanentDelete(loanId)
        return this.service!!.permanentDeletion(result)
    }

}