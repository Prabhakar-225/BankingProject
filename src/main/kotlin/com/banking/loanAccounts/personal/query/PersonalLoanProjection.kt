package com.banking.loanAccounts.personal.query

import com.banking.loanAccounts.personal.api.*
import com.banking.loanAccounts.personal.util.PersonalLoanConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class PersonalLoanProjection(private val repository: PersonalLoanRepository) {

    @EventHandler
    fun createEvent(event: PersonalLoanCreatedEvent) {
        val state = PersonalLoan(
            event.loanId, event.personName, event.accountNumber,
            event.personAddress, event.personalDetails, event.qualificationDetails,
            event.bankName, event.accountNumber, event.ifscCode, event.branchLocation,
            event.grantedAmount, event.loanInterest, event.loanTimePeriod, event.bankId,
            event.deleted
        )
        this.repository.save(state)
    }

    @EventHandler
    fun updateEvent(event: PersonalLoanUpdatedEvent) {
        val existState = this.repository.findById(event.loanId!!).map { r ->
            r.loanId = event.loanId
            r.personName = event.personName
            r.personNumber = event.personNumber
            r.personAddress = event.personAddress
            r.personalDetails = event.personalDetails
            r.qualificationDetails = event.qualificationDetails
            r.bankName = event.bankName
            r.ifscCode = event.ifscCode
            r.branchLocation = event.branchLocation
            r.grantedAmount = event.grantedAmount
            r.loanInterest = event.loanInterest
            r.loanTimePeriod = event.loanTimePeriod
            r.bankId = event.bankId
            r.deleted = event.deleted
            r
        }
        this.repository.save(existState.get())
    }

    @EventHandler
    fun temporaryDeleteEvent(event: PersonalLoanDeletedEvent){
        val record = this.repository.findById(event.loanId!!)
        if(record.isPresent){
            record.get().deleted = true
            this.repository.save(record.get())
        }
    }

    @EventHandler
    fun restoreDeletedEvent(event: PersonalLoanRestoredEvent){
        val record = this.repository.findById(event.loanId!!)
        if(record.isPresent){
            record.get().deleted = false
            this.repository.save(record.get())
        }
    }

    @EventHandler
    fun permanentDeleteEvent(event: PersonalLoanPermanentDeletedEvent){
        val record = this.repository.findById(event.loanId!!)
        if(record.isPresent)
            this.repository.delete(record.get())
    }

    @QueryHandler(queryName = "findAllLoanAccounts")
    fun findAllAccounts(): List<PersonalLoanDto>{
        val accounts = this.repository.findAll()
        if(accounts.isNotEmpty()){
            val dto = accounts.filter { r -> r.deleted == false }.map { state ->
                PersonalLoanDto(state.loanId,state.personName,state.personNumber,state.personAddress,
                    state.personalDetails,state.qualificationDetails,state.bankName,state.accountNumber,
                    state.ifscCode,state.branchLocation,state.grantedAmount,state.loanInterest,
                    state.loanTimePeriod, state.bankId,state.deleted)
            }
            return dto
        }else
            return emptyList()
    }

    @QueryHandler(queryName = "findPersonalLoanById")
    fun findPersonalLoanAccount(loanId: String): PersonalLoanDto{
        val result = this.repository.findById(loanId)
        if(result.isPresent && result.get().deleted == false)
            return PersonalLoanConverter.convertModelToDto(result.get())
        else
            return PersonalLoanDto()
    }
}