package com.banking.practice.shcoolManagement.query

import com.banking.practice.shcoolManagement.api.SchoolCreatedEvent
import com.banking.practice.studentManagement.api.StudentCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class SchoolProjection(private val  repository: SchoolRepository) {

    @EventHandler
    fun create(event: SchoolCreatedEvent){
        val state = SchoolState()
        state.schoolId = event.schoolId
        state.schoolName = event.schoolName
        state.schoolAddress = event.schoolAddress

        this.repository.save(state)
    }
}