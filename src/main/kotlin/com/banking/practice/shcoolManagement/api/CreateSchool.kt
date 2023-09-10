package com.banking.practice.shcoolManagement.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateSchoolCommand (
    @TargetAggregateIdentifier
    var schoolId:String? = null,
    var schoolName : String ? =null,
    var schoolAddress : String? = null
)

data class SchoolCreatedEvent(
    var schoolId:String? = null,
    var schoolName : String ? =null,
    var schoolAddress : String? = null
)

data class CreateSchoolDTO(
    var schoolId:String? = null,
    var schoolName : String ? =null,
    var schoolAddress : String? = null
)

data class SchoolDto(
    var schoolId:String? = null,
    var schoolName : String ? =null,
    var schoolAddress : String? = null
)