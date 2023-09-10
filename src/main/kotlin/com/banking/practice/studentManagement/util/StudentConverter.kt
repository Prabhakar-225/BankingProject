package com.banking.practice.studentManagement.util

import com.banking.practice.studentManagement.api.CreateStudentCommand
import com.banking.practice.studentManagement.api.CreateStudentDTO
import com.banking.practice.studentManagement.api.StudentDto
import com.banking.practice.studentManagement.query.StudentModel
import java.util.UUID

object StudentConverter {

    fun convertModelToDTO(state: StudentModel): StudentDto {
        return StudentDto(state.stdId, state.stdName, state.phone, state.address)
    }

    fun convertDtoToCreate(command: CreateStudentDTO): CreateStudentCommand {
        return CreateStudentCommand(UUID.randomUUID().toString(), command.stdName, command.phone, command.address)
    }
}