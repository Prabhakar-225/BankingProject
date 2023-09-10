package com.banking.fixedDepositAccounts.men.query

import com.banking.fixedDepositAccounts.jointFamily.api.JointFamilyDto
import com.banking.fixedDepositAccounts.jointFamily.util.JointFamilyConverter
import com.banking.fixedDepositAccounts.men.api.*
import com.banking.fixedDepositAccounts.men.util.MenSchemeConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class MenSchemesProjection(private val repository: MenSchemesRepository) {

    @EventHandler
    fun handlerForCreate(event: MenSchemeCreatedEvent) {
        val state = MenSchemes()

        state.menSchemeId = event.menSchemeId
        state.schemeName = event.schemeName
        state.purposeOfScheme = event.purposeOfScheme
        state.accountNumber = event.accountNumber
        state.nameOfPerson = event.nameOfPerson
        state.phoneNumber = event.phoneNumber
        state.address = event.address
        state.bankName = event.bankName
        state.branchLocation = event.branchLocation
        state.ifscCode = event.ifscCode
        state.depositBalance = event.depositBalance
        state.bankId = event.bankId
        state.deleted = event.deleted
        this.repository.save(state)
    }

    @EventHandler
    fun handlerForUpdate(event: MenSchemeUpdatedEvent) {
        val account = this.repository.findById(event.menSchemeId!!)

        if (account.isPresent) {
            account.get().schemeName = event.schemeName
            account.get().purposeOfScheme = event.purposeOfScheme
            account.get().nameOfPerson = event.nameOfPerson
            account.get().phoneNumber = event.phoneNumber
            account.get().address = event.address
            account.get().bankName = event.bankName
            account.get().branchLocation = event.branchLocation
            account.get().ifscCode = event.ifscCode
            account.get().depositBalance = event.depositBalance
            account.get().bankId = event.bankId
            account.get().deleted = event.deleted
            this.repository.save(account.get())
        }
    }

    @EventHandler
    fun handlerForDelete(event: MenSchemeDeletedEvent) {
        val account = repository.findById(event.menSchemeId!!)
        if (account.isPresent) {
            account.get().deleted = true
            repository.save(account.get())
        }
    }

    @EventHandler
    fun handlerForPermanentDelete(event: MenSchemePermanentDeletedEvent) {
        val account = repository.findById(event.menSchemeId!!)
        if (account.isPresent) {
            repository.delete(account.get())
        }
    }


    @EventHandler
    fun handlerForRestore(event: DeletedMenSchemeRestoredEvent) {
        val account = repository.findById(event.menSchemeId!!)
        if (account.isPresent) {
            account.get().deleted = false
            repository.save(account.get())
        }
    }

    @QueryHandler(queryName = "findAllMenSchemeAccounts")
    fun findAllAccounts(): List<MenSchemeDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { x ->
                MenSchemeDto(
                    x.menSchemeId, x.schemeName, x.purposeOfScheme, x.accountNumber,
                    x.nameOfPerson, x.phoneNumber, x.address, x.bankName, x.branchLocation,
                    x.ifscCode, x.depositBalance, x.bankId, x.deleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    @QueryHandler(queryName = "findAccountById")
    fun findAccountById(menSchemeId: String): MenSchemeDto {
        val result = repository.findById(menSchemeId)
        if (result.isPresent && result.get().deleted == false) {
            return MenSchemeConverter.convertModelToDTO(result.get())
        }
        return MenSchemeDto()
    }
}
