package com.banking.practice.studentManagement.service

import com.banking.configurations.ResponseWithError
import com.banking.practice.studentManagement.api.CreateStudentCommand
import com.banking.practice.studentManagement.api.StudentDto
import com.banking.practice.studentManagement.query.StudentRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class StudentService(
    private val repository: StudentRepository,
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    private val logger = LoggerFactory.getLogger(StudentService::class.java)


    fun createStudent(command: CreateStudentCommand): CompletableFuture<ResponseWithError<String>>{
        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of("Student Successfully Created with -> "+command.stdId)
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }

    fun findStudent(stdId: String): CompletableFuture<ResponseWithError<StudentDto>>{
        val result = queryGateway.query("findStudentBy",stdId,
            ResponseTypes.instanceOf(StudentDto::class.java))
        return result.thenApply { x ->
            if (x.stdId == null) ResponseWithError.ofError("StudentId null found")
            else ResponseWithError.of(x)
        }
            .exceptionally { e -> ResponseWithError.ofError(e.message) }
    }


}