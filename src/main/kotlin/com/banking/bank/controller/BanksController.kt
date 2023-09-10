package com.banking.bank.controller


import com.banking.bank.api.AddAccountsToBankDTO
import com.banking.bank.api.BankDto
import com.banking.bank.api.CreateBankDTO
import com.banking.bank.api.UpdateBankDto
import com.banking.bank.service.BankService
import com.banking.bank.util.BankConverter
import com.banking.configurations.ResponseWithError
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
@RequestMapping("/banks")
class BanksController {

    @Autowired
    private val service: BankService? = null


    //create Bank
    @PostMapping("/createBank")
    fun createBank(@RequestBody  createBankDto: CreateBankDTO): CompletableFuture<ResponseWithError<String>> {
        val result= BankConverter.convertDtoToCommand(createBankDto)
        return service!!.createBank(result)
    }


    @PutMapping("/updateBank")
    fun updateBank(@RequestBody @Valid updateBankDto: UpdateBankDto): CompletableFuture<ResponseWithError<String>>{
        val result = BankConverter.convertDtoToCommandToUpdateBank(updateBankDto)
        return  service!!.updateBank(result)
    }

    @PutMapping("/deletedBank/{bankId}")
    fun deleteBank(@PathVariable("bankId") bankId: String): CompletableFuture<ResponseWithError<String>>{
        val result = BankConverter.convertDtoToCommandToBankDelete(bankId)
        return service!!.deleteBank(result)
    }

    @DeleteMapping("/deleteBankPermanently/{bankId}")
    fun deleteBankPermanent(@PathVariable("bankId") bankId: String): CompletableFuture<ResponseWithError<String>>{
        val result = BankConverter.convertDtoToCommandToBankPermanentDelete(bankId)
        return service!!.deleteBankPermanent(result)
    }

    @GetMapping("/findAllBanks")
    fun findAllBanks():CompletableFuture<ResponseWithError<List<BankDto>>>{
        return this.service!!.findAllBanks()
    }

    @GetMapping("/findByBankId/{bankId}")
    fun findByBankId(@PathVariable("bankId") bankId: String): CompletableFuture<ResponseWithError<BankDto>>{
        return this.service!!.findByBankId(bankId)
    }




}