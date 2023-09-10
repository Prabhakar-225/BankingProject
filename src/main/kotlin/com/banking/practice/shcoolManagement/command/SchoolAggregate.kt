package com.banking.practice.shcoolManagement.command

import com.banking.practice.shcoolManagement.api.CreateSchoolCommand
import com.banking.practice.shcoolManagement.api.SchoolCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class SchoolAggregate {

    @AggregateIdentifier
    var schoolId:String? = null
    var schoolName : String ? =null
    var schoolAddress : String? = null

    constructor()

    @CommandHandler
    constructor(command: CreateSchoolCommand){
        AggregateLifecycle.apply(
            SchoolCreatedEvent(command.schoolId,command.schoolName,command.schoolAddress))
    }

    @EventSourcingHandler
    fun handler(event: SchoolCreatedEvent){
        this.schoolId = event.schoolId
        this.schoolName = event.schoolName
        this.schoolAddress = event.schoolAddress
    }
}

