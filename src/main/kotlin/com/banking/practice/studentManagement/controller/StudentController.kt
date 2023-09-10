package com.banking.practice.studentManagement.controller

import com.banking.configurations.ResponseWithError
import com.banking.practice.studentManagement.api.CreateStudentDTO
import com.banking.practice.studentManagement.api.StudentDto
import com.banking.practice.studentManagement.service.StudentService
import com.banking.practice.studentManagement.util.StudentConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.validation.Valid

@RestController
@RequestMapping("/students")
class StudentController {

    @Autowired
    private val service: StudentService? = null

    @PostMapping("/createStudent")
    fun createStudent(@RequestBody @Valid create: CreateStudentDTO): CompletableFuture<ResponseWithError<String>>{
        val result = StudentConverter.convertDtoToCreate(create)
        return this.service!!.createStudent(result)
    }

    @GetMapping("/findStudentBy/{stdId}")
    fun findStudent(@PathVariable("stdId") stdId: String): CompletableFuture<ResponseWithError<StudentDto>>{
        return this.service!!.findStudent(stdId)
    }
}