package com.banking.fixedDepositAccounts.jointFamily.query

import com.banking.fixedDepositAccounts.jointFamily.api.*
import com.banking.fixedDepositAccounts.jointFamily.util.JointFamilyConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class JointFamilyProjection(private val repository: JointFamilyRepository) {

    //create Account
    @EventHandler
    fun saveAccount(event: JointFamilyCreatedEvent) {
        val state = JointFamily()

        state.jointFamilyId = event.jointFamilyId
        state.accountNumber = event.accountNumber
        state.schemeName = event.schemeName
        state.jointFamilyName = event.jointFamilyName
        state.jointFamilyAddress = event.jointFamilyAddress
        state.personsToInvest = event.personsToInvest
        state.personsNames = event.personsNames
        state.personsPhoneNumbers = event.personsPhoneNumbers
        state.bankName = event.bankName
        state.branchLocation = event.branchLocation
        state.ifscCode = event.ifscCode
        state.depositMoney = event.depositMoney
        state.bankId = event.bankId
        state.deleted = event.deleted
        repository.save(state)
    }

    //update Account
    @EventHandler
    fun handlerForUpdateAccountEvent(updatedEvent: JointFamilyUpdatedEvent) {
        val account = repository.findById(updatedEvent.jointFamilyId!!)

        if (account.isPresent) {
            account.get().jointFamilyId = updatedEvent.jointFamilyId
            account.get().schemeName = updatedEvent.schemeName
            account.get().jointFamilyName = updatedEvent.jointFamilyName
            account.get().jointFamilyAddress = updatedEvent.jointFamilyAddress
            account.get().personsToInvest = updatedEvent.personsToInvest
            account.get().personsNames = updatedEvent.personsNames
            account.get().personsPhoneNumbers = updatedEvent.personsPhoneNumbers
            account.get().bankName = updatedEvent.bankName
            account.get().branchLocation = updatedEvent.branchLocation
            account.get().ifscCode = updatedEvent.ifscCode
            account.get().depositMoney = updatedEvent.depositMoney
            account.get().bankId = updatedEvent.bankId
            account.get().deleted = updatedEvent.deleted

            repository.save(account.get())
        }
    }

    // Soft core deleted Account
    @EventHandler
    fun handlerForDeleteAccountTemporary(event: JointFamilyDeletedEvent) {
        val account = repository.findById(event.jointFamilyId!!)
        if (account.isPresent) {
            account.get().deleted = true
            repository.save(account.get())
        }
    }

    //delete permanent
    @EventHandler
    fun deletePermanent(event: JointFamilyPermanentDeletedEvent){
        val account = repository.findById(event.jointFamilyId!!)
        if (account.isPresent) {
            repository.delete(account.get())
        }
    }

    //Restore
    @EventHandler
    fun restoreAccount(event: JointFamilyRestoredEvent) {
        val account = repository.findById(event.jointFamilyId!!)
        if (account.isPresent) {
            account.get().deleted = false
            this.repository.save(account.get())
        }
    }

    //find All Accounts
    @QueryHandler(queryName = "findAllJointFamilyAccounts")
    fun findAllJointFamilyAccounts(): List<JointFamilyDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { x ->
                JointFamilyDto(
                    x.jointFamilyId,x.accountNumber, x.schemeName,
                    x.jointFamilyName,x.jointFamilyAddress,x.personsToInvest,
                    x.personsNames,x.personsPhoneNumbers,x.bankName,x.branchLocation,
                    x.ifscCode, x.depositMoney,x.bankId,x.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    // find Account By accountId
    @QueryHandler(queryName = "findJointFamilyById")
    fun findJointFamilyAccountById(jointFamilyId: String): JointFamilyDto {
        val result = repository.findById(jointFamilyId)
        if (result.isPresent && result.get().deleted == false) {
            return JointFamilyConverter.convertModelToDTO(result.get())
        }
        return JointFamilyDto()
    }
}