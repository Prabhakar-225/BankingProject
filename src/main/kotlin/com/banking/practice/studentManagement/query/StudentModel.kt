package com.banking.practice.studentManagement.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class StudentModel (
    @Id
    var stdId: String? = null,
    var stdName: String? = null,
    var phone: Long? = 0L,
    var address: String? = null
)