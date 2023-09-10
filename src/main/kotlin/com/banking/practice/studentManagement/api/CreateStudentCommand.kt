package com.banking.practice.studentManagement.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateStudentCommand(
    @TargetAggregateIdentifier
    var stdId: String? = null,
    var stdName: String? = null,
    var phone: Long? = 0L,
    var address: String? = null
)

data class StudentCreatedEvent(
    var stdId: String? = null,
    var stdName: String? = null,
    var phone: Long? = 0L,
    var address: String? = null
)

data class CreateStudentDTO(
    var stdId: String? = null,
    var stdName: String? = null,
    var phone: Long? = 0L,
    var address: String? = null
)

data class StudentDto(
    var stdId: String? = null,
    var stdName: String? = null,
    var phone: Long? = 0L,
    var address: String? = null
)