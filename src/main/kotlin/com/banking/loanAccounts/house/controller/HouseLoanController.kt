package com.banking.loanAccounts.house.controller

import com.banking.configurations.ResponseWithError
import com.banking.loanAccounts.house.api.*
import com.banking.loanAccounts.house.service.HouseLoanService
import com.banking.loanAccounts.house.util.HouseLoanConverter
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
@RequestMapping("/houseLoans")
class HouseLoanController {

    @Autowired
    private val service: HouseLoanService? = null

    @PostMapping("/createHouseLoan")
    fun create(@RequestBody @Valid createDTO: CreateHouseLoanDTO): CompletableFuture<ResponseWithError<String>>{
        val result = HouseLoanConverter.convertDtoToCommandToCreate(createDTO)
        return this.service!!.createHouseLoan(result)
    }

    @PutMapping("/updateHouseLoan")
    fun update(@RequestBody @Valid updateDto: UpdateHouseLoanDTO): CompletableFuture<ResponseWithError<String>>{
        val result = HouseLoanConverter.convertDtoToCommandToUpdate(updateDto)
        return this.service!!.updateHouseLoan(result)
    }

    @GetMapping("/findAllHouseLoanAccounts")
    fun findAll(): CompletableFuture<ResponseWithError<List<HouseLoanDto>>>{
        return this.service!!.findAllAccounts()
    }

    @GetMapping("/findDetailsByLoanId/{loanId}")
    fun findById(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<HouseLoanDto>>{
        return this.service!!.findByLoanId(loanId)
    }

    @PutMapping("/temporaryDeletion/{loanId}")
    fun deleteAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = HouseLoanConverter.convertDtoToCommandToDelete(loanId)
        return this.service!!.temporaryDeletion(result)
    }

    @PutMapping("/restoreDeletedAccount/{loanId}")
    fun restoreAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = HouseLoanConverter.convertDtoToCommandToRestore(loanId)
        return this.service!!.restoreDeletedAccount(result)
    }

    @DeleteMapping("/permanentDeletion/{loanId}")
    fun permanentDeleteAccount(@PathVariable("loanId") loanId: String): CompletableFuture<ResponseWithError<String>>{
        val result = HouseLoanConverter.convertDtoToCommandToPermanentDelete(loanId)
        return this.service!!.permanentDeletion(result)
    }

}