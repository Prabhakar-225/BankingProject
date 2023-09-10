package com.banking.loanAccounts.instant.controller

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.instant.api.CreateInstantLoanDTO
import com.banking.loanAccounts.instant.api.InstantLoanDto
import com.banking.loanAccounts.instant.api.UpdateInstantLoanDTO
import com.banking.loanAccounts.instant.query.InstantLoan
import com.banking.loanAccounts.instant.service.InstantLoanService
import com.banking.loanAccounts.instant.util.InstantLoanConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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
@RequestMapping("/instantLoans")
class InstantLoanController {

    @Autowired
    private val service: InstantLoanService? = null

    @PostMapping("/createInstantLoan")
    fun create(@RequestBody @Valid createDTO: CreateInstantLoanDTO): CompletableFuture<ResponseWithError<String>> {
        val result = InstantLoanConverter.convertDtoToCommandToCreate(createDTO)
        return this.service!!.createInstantLoanAccount(result)
    }

    @PutMapping("/updateInstantLoan")
    fun update(@RequestBody @Valid updateDTO: UpdateInstantLoanDTO): CompletableFuture<ResponseWithError<String>> {
        val result = InstantLoanConverter.convertDtoToCommandToUpdate(updateDTO)
        return this.service!!.updateInstantLoanAccount(result)
    }

    @GetMapping("/findAllInstantLoanAccounts")
    fun findAll(): CompletableFuture<ResponseWithError<List<InstantLoanDto>>> {
        return this.service!!.findAllInstantLoanAccounts()
    }

    @GetMapping("/findByLoanId/{loanId}")
    fun findAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<InstantLoanDto>> {
        return this.service!!.findInstantLoanAccountByLoanId(loanId)
    }

    @PutMapping("/temporaryDeletion/{loanId}")
    fun temporaryDeletion(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = InstantLoanConverter.convertDtoToCommandToDelete(loanId)
        return this.service!!.temporaryDeletion(result)
    }

    @PutMapping("/restoreDeletedAccount/{loanId}")
    fun restoreDeletedAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = InstantLoanConverter.convertDtoToCommandToRestore(loanId)
        return this.service!!.restoreDeletedInstantLoan(result)
    }

    @DeleteMapping("/deleteAccount/{loanId}")
    fun permanentDeletion(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = InstantLoanConverter.convertDtoToCommandToDeletePermanent(loanId)
        return this.service!!.permanentDeletion(result)
    }



}