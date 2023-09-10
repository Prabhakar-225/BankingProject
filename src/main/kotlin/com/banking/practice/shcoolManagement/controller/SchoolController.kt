package com.banking.practice.shcoolManagement.controller

import com.banking.configurations.ResponseWithError
import com.banking.practice.shcoolManagement.api.CreateSchoolDTO
import com.banking.practice.shcoolManagement.service.SchoolService
import com.banking.practice.shcoolManagement.util.SchoolConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/school")
class SchoolController {

    @Autowired
    private val service : SchoolService? = null

    @PostMapping("/createSchool")
    fun create(@RequestBody @Valid dto: CreateSchoolDTO): CompletableFuture<ResponseWithError<String>>{
        val result = SchoolConverter.convertDToToCreate(dto)
        return this.service!!.create(result)
    }
}