package com.banking.practice.shcoolManagement.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class SchoolState(
        @Id
        var schoolId:String? = null,
        var schoolName : String ? =null,
        var schoolAddress : String? = null

)