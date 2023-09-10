package com.banking.practice.studentManagement.command

import com.banking.practice.studentManagement.api.CreateStudentCommand
import com.banking.practice.studentManagement.api.StudentCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class StudentAggregate {

    @AggregateIdentifier
    var stdId: String? = null
    var stdName: String? = null
    var phone: Long? = 0L
    var address: String? = null

    constructor()

    @CommandHandler
    constructor(command: CreateStudentCommand){
        AggregateLifecycle.apply(StudentCreatedEvent(command.stdId,command.stdName,command.phone,command.address))
    }

    @EventSourcingHandler
    fun createEvent(event: StudentCreatedEvent){
        this.stdId = event.stdId
        this.stdName = event.stdName
        this.phone = event.phone
        this.address = event.address
    }


}