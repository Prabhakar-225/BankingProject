package com.banking.practice.shcoolManagement.util

import com.banking.practice.shcoolManagement.api.CreateSchoolCommand
import com.banking.practice.shcoolManagement.api.CreateSchoolDTO
import java.util.UUID

object SchoolConverter {

    fun convertDToToCreate(createDto: CreateSchoolDTO):CreateSchoolCommand{
        return CreateSchoolCommand(UUID.randomUUID().toString(),createDto.schoolName,createDto.schoolAddress)
    }
}