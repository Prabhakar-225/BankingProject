package com.banking.fixedDepositAccounts.jointFamily.controller

import com.banking.configurations.ResponseWithError
import com.banking.fixedDepositAccounts.jointFamily.api.CreateJointFamilyDTO
import com.banking.fixedDepositAccounts.jointFamily.api.JointFamilyDto
import com.banking.fixedDepositAccounts.jointFamily.api.UpdateJointFamilyDTO
import com.banking.fixedDepositAccounts.jointFamily.service.JointFamilyService
import com.banking.fixedDepositAccounts.jointFamily.util.JointFamilyConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/jointFamilySchemes")
class JointFamilyController {

    @Autowired
    private val service: JointFamilyService? = null


    @PostMapping("/createJointFamilyAccount")
    fun createWomenFixDeposit(@RequestBody @Valid createDTO: CreateJointFamilyDTO): CompletableFuture<ResponseWithError<String>> {
        val result = JointFamilyConverter.convertDtoToCommand(createDTO)
        return this.service!!.createJointFamilyScheme(result)
    }

    @PutMapping("/updateJointFamilyAccount")
    fun updateWomenFixDeposit(@RequestBody @Valid updateDTO: UpdateJointFamilyDTO): CompletableFuture<ResponseWithError<String>> {
        val result = JointFamilyConverter.convertDtoToCommandToUpdateAccount(updateDTO)
        return this.service!!.updateJointFamilyScheme(result)
    }

    @GetMapping("/findAllJointFamilyAccounts")
    fun findAll(): CompletableFuture<ResponseWithError<List<JointFamilyDto>>> {
        return this.service!!.findAllAccounts()
    }

    @GetMapping("/findJointFamilyById/{jointFamilyId}")
    fun findById(@PathVariable("jointFamilyId") jointFamilyId: String): CompletableFuture<ResponseWithError<JointFamilyDto>> {
        return this.service!!.findJointFamilyAccountById(jointFamilyId)
    }

    @PutMapping("/deleteJointFamilyAccount/{jointFamilyId}")
    fun deleteAccount(@PathVariable("jointFamilyId") jointFamilyId: String): CompletableFuture<ResponseWithError<String>> {
        val result = JointFamilyConverter.convertDtoToCommandToAccountDelete(jointFamilyId)
        return this.service!!.deleteAccount(result)
    }

    @DeleteMapping("/deleteJointFamilyAccountPermanently/{jointFamilyId}")
    fun permanentDeleteAccount(@PathVariable("jointFamilyId") jointFamilyId: String): CompletableFuture<ResponseWithError<String>> {
        val result = JointFamilyConverter.convertDtoToCommandToAccountDeletePermanently(jointFamilyId)
        return this.service!!.deleteAccountPermanently(result)
    }

    @PutMapping("/restoreJointFamilyAccount/{jointFamilyId}")
    fun restoreAccount(@PathVariable("jointFamilyId") jointFamilyId: String): CompletableFuture<ResponseWithError<String>> {
        val result = JointFamilyConverter.convertDtoToCommandToAccountRestore(jointFamilyId)
        return this.service!!.restoreAccount(result)
    }



}