package com.banking.fixedDepositAccounts.men.controller

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.men.api.CreateMenSchemeDTO
import com.banking.fixedDepositAccounts.men.api.MenSchemeDto
import com.banking.fixedDepositAccounts.men.api.UpdateMenSchemeDTO
import com.banking.fixedDepositAccounts.men.service.MenSchemeService
import com.banking.fixedDepositAccounts.men.util.MenSchemeConverter
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
@RequestMapping("/menSchemes")
class MenSchemeController {

    @Autowired
    private val service: MenSchemeService? = null

    @PostMapping("/createMenSchemeAccount")
    fun createMenSchemeAccount(@RequestBody @Valid createDTO: CreateMenSchemeDTO): CompletableFuture<ResponseWithError<String>>{
        val result = MenSchemeConverter.convertDtoToCommand(createDTO)
        return this.service!!.createAccount(result)
    }

    @PutMapping("/updateMenSchemeAccount")
    fun updateMenSchemeAccount(@RequestBody @Valid updateDTO: UpdateMenSchemeDTO): CompletableFuture<ResponseWithError<String>>{
        val result = MenSchemeConverter.convertDtoToCommandToUpdateAccount(updateDTO)
        return this.service!!.updateAccount(result)
    }

    @GetMapping("/findAllMenSchemeAccounts")
    fun findAllMenSchemeAccounts():CompletableFuture<ResponseWithError<List<MenSchemeDto>>>{
        return this.service!!.findAllAccounts()
    }

    @GetMapping("/findAccountById/{menSchemeId}")
    fun findMenSchemeAccountById(@PathVariable("menSchemeId") menSchemeId: String): CompletableFuture<ResponseWithError<MenSchemeDto>>{
        return this.service!!.findAccountById(menSchemeId)
    }

    @PutMapping("/deleteTemporaryAccount/{menSchemeId}")
    fun temporaryDeleteAccount(@PathVariable("menSchemeId") menSchemeId: String): CompletableFuture<ResponseWithError<String>>{
        val result = MenSchemeConverter.convertDtoToCommandToAccountDelete(menSchemeId)
        return  this.service!!.deleteAccount(result)
    }

    @PutMapping("restoreDeletedAccount/{menSchemeId}")
    fun restoreDeletedAccount(@PathVariable("menSchemeId") menSchemeId: String): CompletableFuture<ResponseWithError<String>>{
        val result = MenSchemeConverter.convertDtoToCommandToAccountRestore(menSchemeId)
        return this.service!!.restoreDeletedAccount(result)
    }

    @DeleteMapping("/DeletePermanently/{menSchemeId}")
    fun permanentDeleteAccount(@PathVariable("menSchemeId") menSchemeId: String): CompletableFuture<ResponseWithError<String>>{
        val result = MenSchemeConverter.convertDtoToCommandToAccountDeletePermanently(menSchemeId)
        return this.service!!.permanentDeleteAccount(result)
    }
}