package com.banking.practice.shcoolManagement.service

import com.banking.configurations.ResponseWithError
import com.banking.practice.shcoolManagement.api.CreateSchoolCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class SchoolService(private val commandGateway: CommandGateway) {

    private val  logger = LoggerFactory.getLogger(SchoolService::class.java)

    fun create(command: CreateSchoolCommand): CompletableFuture<ResponseWithError<String>>{

        val result = commandGateway.send<Any>(command).thenApply {r ->
            ResponseWithError.of("Success "+command.schoolId)
        }.exceptionally { e ->
            logger.error(e.message)
            ResponseWithError.ofError(e.message)
        }
        return result
    }



}