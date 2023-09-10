package com.banking.fixedDepositAccounts.girlsScheme.controller

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.girlsScheme.api.CreateGirlsSchemeDTO
import com.banking.fixedDepositAccounts.girlsScheme.api.GirlsSchemeDto
import com.banking.fixedDepositAccounts.girlsScheme.api.UpdateGirlsSchemeDTO
import com.banking.fixedDepositAccounts.girlsScheme.service.GirlsSchemeService
import com.banking.fixedDepositAccounts.girlsScheme.util.GirlsSchemeConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/girlsSchemes")
class GirlsSchemeController {

    @Autowired
    private val service: GirlsSchemeService? = null

    @PostMapping("/createGilsSchemeAccount")
    fun createWomenFixDeposit(@RequestBody @Valid createDTO: CreateGirlsSchemeDTO): CompletableFuture<ResponseWithError<String>> {
        val result = GirlsSchemeConverter.convertDtoToCommand(createDTO)
        return this.service!!.createGirlsSchemeAccount(result)
    }

    @PutMapping("/updateGirlsSchemeAccount")
    fun updateWomenFixDeposit(@RequestBody @Valid updateDto: UpdateGirlsSchemeDTO): CompletableFuture<ResponseWithError<String>> {
        val result = GirlsSchemeConverter.convertDtoToCommandToUpdateAccount(updateDto)
        return this.service!!.updateGirlsSchemeAccount(result)
    }

    @GetMapping("/findAllGirlsSchemeAccounts")
    fun findAll(): CompletableFuture<ResponseWithError<List<GirlsSchemeDto>>> {
        return this.service!!.findAllGirlsSchemeAccounts()
    }

    @GetMapping("/findGirlsSchemeById/{girlsSchemeId}")
    fun findById(@PathVariable("girlsSchemeId") girlsSchemeId: String): CompletableFuture<ResponseWithError<GirlsSchemeDto>> {
        return this.service!!.findGirlsSchemeById(girlsSchemeId)
    }

    @PutMapping("/deleteGirlsSchemeAccount/{girlsSchemeId}")
    fun deleteAccount(@PathVariable("girlsSchemeId") girlsSchemeId: String): CompletableFuture<ResponseWithError<String>> {
        val result = GirlsSchemeConverter.convertDtoToCommandToAccountDelete(girlsSchemeId)
        return this.service!!.deleteGirlsSchemeAccount(result)
    }

    @DeleteMapping("/deleteGirlsSchemeAccountPermanently/{girlsSchemeId}")
    fun permanentDeleteAccount(@PathVariable("girlsSchemeId") girlsSchemeId: String): CompletableFuture<ResponseWithError<String>> {
        val result = GirlsSchemeConverter.convertDtoToCommandToAccountDeletePermanently(girlsSchemeId)
        return this.service!!.deletePermanentGirlsSchemeAccount(result)
    }

    @PutMapping("/restoreGirlsSchemeAccount/{girlsSchemeId}")
    fun restoreAccount(@PathVariable("girlsSchemeId") girlsSchemeId: String): CompletableFuture<ResponseWithError<String>> {
        val result = GirlsSchemeConverter.convertDtoToCommandToAccountRestore(girlsSchemeId)
        return this.service!!.restoreGirlsSchemeAccount(result)
    }
}