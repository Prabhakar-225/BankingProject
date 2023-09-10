package com.banking.practice.studentManagement.query

import com.banking.accounts.api.AccountCreatedEvent
import com.banking.accounts.api.AccountDto
import com.banking.accounts.query.AccountState
import com.banking.accounts.util.AccountConvertor
import com.banking.practice.studentManagement.api.StudentCreatedEvent
import com.banking.practice.studentManagement.api.StudentDto
import com.banking.practice.studentManagement.util.StudentConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class StudentProjection(private val repository: StudentRepository) {


    @EventHandler
    fun saveAccount(event: StudentCreatedEvent) {
        val state = StudentModel()
        state.stdId = event.stdId
        state.stdName = event.stdName
        state.phone = event.phone
        state.address = event.address

        this.repository.save(state)
    }


    @QueryHandler(queryName = "findStudentBy")
    fun findById(stdId: String): StudentDto {
        val result = repository.findById(stdId)
        if (result.isPresent) {
            return StudentConverter.convertModelToDTO(result.get())
        }
        return StudentDto()
    }
}