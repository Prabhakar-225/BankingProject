package com.banking.fixedDepositAccounts.women.controller

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.women.api.CreateWomenFixDepositCommand
import com.banking.fixedDepositAccounts.women.api.CreateWomenFixDepositDTO
import com.banking.fixedDepositAccounts.women.api.UpdateWomenFixDepositDTO
import com.banking.fixedDepositAccounts.women.api.WomenFixedDepositDto
import com.banking.fixedDepositAccounts.women.service.WomenFixDepositService
import com.banking.fixedDepositAccounts.women.util.WomenFixDepositConverter
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
@RequestMapping("/womenFixDeposits")
class WomenFixDepositController {

    @Autowired
    private val service: WomenFixDepositService? = null


    @PostMapping("/createWomenFixDeposit")
    fun createWomenFixDeposit(@RequestBody @Valid createDTO: CreateWomenFixDepositDTO): CompletableFuture<ResponseWithError<String>> {
        val result = WomenFixDepositConverter.convertDtoToCommand(createDTO)
        return this.service!!.createWomenFixDepositAccount(result)
    }

    @PutMapping("/updateWomenFixDeposit")
    fun updateWomenFixDeposit(@RequestBody @Valid updateDto: UpdateWomenFixDepositDTO): CompletableFuture<ResponseWithError<String>> {
        val result = WomenFixDepositConverter.convertDtoToCommandToUpdateAccount(updateDto)
        return this.service!!.updateWomenFixDepositAccount(result)
    }

    @GetMapping("/findAllFixDepositAccounts")
    fun findAll(): CompletableFuture<ResponseWithError<List<WomenFixedDepositDto>>> {
        return this.service!!.findAllWomenFixDepositAccounts()
    }

    @GetMapping("/findByFixDepositAccountId/{accountId}")
    fun findById(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<WomenFixedDepositDto>> {
        return this.service!!.findWomenFixDepositAccountById(accountId)
    }

    @PutMapping("/deleteWomenFixDepositAccount/{accountId}")
    fun deleteAccount(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<String>> {
        val result = WomenFixDepositConverter.convertDtoToCommandToAccountDelete(accountId)
        return this.service!!.deleteWomenFixDepositAccount(result)
    }

    @DeleteMapping("/deletePermanently/{accountId}")
    fun deleteAccountPermanently(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<String>> {
        val result = WomenFixDepositConverter.convertDtoToCommandToAccountDeletePermanent(accountId)
        return this.service!!.deletePermanentWomenFixDepositAccount(result)
    }

    @PutMapping("/restoreWomenFixDepositAccount/{accountId}")
    fun restoreAccount(@PathVariable("accountId") accountId: String): CompletableFuture<ResponseWithError<String>> {
        val result = WomenFixDepositConverter.convertDtoToCommandToAccountRestore(accountId)
        return this.service!!.restoreWomenFixDepositAccount(result)
    }


}