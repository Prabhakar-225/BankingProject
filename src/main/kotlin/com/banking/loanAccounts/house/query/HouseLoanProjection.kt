package com.banking.loanAccounts.house.query

import com.banking.loanAccounts.house.api.*
import com.banking.loanAccounts.house.util.HouseLoanConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class HouseLoanProjection(private val repository: HouseLoanRepository) {

    @EventHandler
    fun createHouseLoan(event: HouseLoanCreatedEvent){
        val state = HouseLoan()

        state.loanId = event.loanId
        state.consumerName = event.consumerName
        state.consumerNumber = event.consumerNumber
        state.consumerAddress = event.consumerAddress
        state.identityDetails = event.identityDetails
        state.houseDetails = event.houseDetails
        state.houseCents = event.houseCents
        state.bankName = event.bankName
        state.accountNumber = event.accountNumber
        state.ifscCode = event.ifscCode
        state.branchLocation = event.branchLocation
        state.sanctionedAmount = event.sanctionedAmount
        state.loanInterest = event.loanInterest
        state.bankId = event.bankId
        state.deleted = event.deleted

        this.repository.save(state)
    }

    @EventHandler
    fun updateHouseLoan(event: HouseLoanUpdatedEvent){
        val existState = this.repository.findById(event.loanId!!)
        if(existState.isPresent) {
            existState.get().loanId = event.loanId
            existState.get().consumerName = event.consumerName
            existState.get().consumerNumber = event.consumerNumber
            existState.get().consumerAddress = event.consumerAddress
            existState.get().identityDetails = event.identityDetails
            existState.get().houseDetails = event.houseDetails
            existState.get().houseCents = event.houseCents
            existState.get().bankName = event.bankName
            existState.get().ifscCode = event.ifscCode
            existState.get().branchLocation = event.branchLocation
            existState.get().sanctionedAmount = event.sanctionedAmount
            existState.get().loanInterest = event.loanInterest
            existState.get().bankId = event.bankId
            existState.get().deleted = event.deleted
            this.repository.save(existState.get())
        }
    }

    @EventHandler
    fun deleteHoseLoan(event: HouseLoanDeletedEvent){
        val state = this.repository.findById(event.loanId!!)
        if(state.isPresent){
            state.get().deleted = true
            this.repository.save(state.get())
        }
    }

    @EventHandler
    fun restoreDeletedHouseLoan(event: DeletedHouseLoanRestoredEvent){
        val state = this.repository.findById(event.loanId!!)
        if(state.isPresent){
            state.get().deleted = false
            this.repository.save(state.get())
        }
    }

    @EventHandler
    fun permanentDelete(event: HouseLoanPermanentDeletedEvent){
        val state = this.repository.findById(event.loanId!!)
        if(state.isPresent)
            this.repository.delete(state.get())
    }

    @QueryHandler(queryName = "findDetailsByLoanId")
    fun findByLoanId(loanId: String): HouseLoanDto{
        val result = repository.findById(loanId)
        if (result.isPresent && result.get().deleted == false) {
            return HouseLoanConverter.convertModelToDTO(result.get())
        }
        return HouseLoanDto()
    }

    @QueryHandler(queryName = "findAllHouseLoanAccounts")
    fun findAll(): List<HouseLoanDto>{
        val accounts = this.repository.findAll()
        if(accounts.isNotEmpty()){
            val dto = accounts.filter { x -> x.deleted == false }.map { state ->
                HouseLoanDto(state.loanId,state.consumerName,state.consumerNumber,
                    state.consumerAddress,state.identityDetails,state.houseDetails,
                    state.houseCents,state.bankName,state.accountNumber,state.ifscCode,
                    state.branchLocation,state.sanctionedAmount,state.loanInterest,state.bankId,
                    state.deleted)
            }
            return dto
        }else
            return emptyList()
    }
}