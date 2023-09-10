package com.banking.fixedDepositAccounts.girlsScheme.query

import com.banking.bank.query.BanksRepository
import com.banking.fixedDepositAccounts.girlsScheme.api.*
import com.banking.fixedDepositAccounts.girlsScheme.util.GirlsSchemeConverter
import com.banking.fixedDepositAccounts.women.api.*
import com.banking.fixedDepositAccounts.women.util.WomenFixDepositConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class GirlsSchemeProjection(
    private val repository: GirlsSchemeRepository,
    private val bankRepository: BanksRepository,) {

    //create Account
    @EventHandler
    fun saveAccount(event: GirlsSchemeCreatedEvent) {
        val state = GirlsScheme()

        state.girlsSchemeId = event.girlsSchemeId
        state.bankName = event.bankName
        state.accountType = event.accountType
        state.schemeName = event.schemeName
        state.accountNumber = event.accountNumber
        state.girlName = event.girlName
        state.depositBalance = event.depositBalance
        state.branchLocation = event.branchLocation
        state.ifscCode = event.ifscCode
        state.address = event.address
        state.deleted = event.deleted
        state.bankId = event.bankId
        repository.save(state)
    }

    //update Account
    @EventHandler
    fun handlerForUpdateAccountEvent(updatedEvent: GirlsSchemeUpdatedEvent) {
        val scheme = repository.findById(updatedEvent.girlsSchemeId!!)

        if (scheme.isPresent) {
            scheme.get().bankName = updatedEvent.bankName
            scheme.get().accountType = updatedEvent.accountType
            scheme.get().girlName = updatedEvent.girlName
            scheme.get().depositBalance = updatedEvent.depositBalance
            scheme.get().branchLocation = updatedEvent.branchLocation
            scheme.get().ifscCode = updatedEvent.ifscCode
            scheme.get().address = updatedEvent.address
            scheme.get().deleted = updatedEvent.deleted
            scheme.get().bankId = updatedEvent.bankId

            repository.save(scheme.get())
        }
    }

    // Soft core deleted Account
    @EventHandler
    fun deleteGirlsSchemeAccountTemporary(event: GirlsSchemeDeletedEvent) {
        val account = repository.findById(event.girlsSchemeId!!)
        if (account.isPresent) {
            account.get().deleted = true
            repository.save(account.get())
        }
    }

    //delete permanent
    @EventHandler
    fun deletePermanent(event: GirlsSchemePermanentDeletedEvent){
        val account = repository.findById(event.girlsSchemeId!!)
        if (account.isPresent) {
            repository.delete(account.get())
        }
    }

    //Restore
    @EventHandler
    fun restoreGirlsSchemeAccount(event: GirlsSchemeRestoredEvent) {
        val account = repository.findById(event.girlsSchemeId!!)
        if (account.isPresent) {
            account.get().deleted = false
            this.repository.save(account.get())
        }
    }

    //find All Accounts
    @QueryHandler(queryName = "findAllGirlsSchemeAccounts")
    fun findAllGirlsSchemeAccounts(): List<GirlsSchemeDto> {
        val accounts = this.repository.findAll()
        if (accounts.isNotEmpty()) {
            val dto = accounts.filter { x -> x.deleted == false }.map { x ->
                GirlsSchemeDto(
                    x.girlsSchemeId, x.bankName, x.accountType,x.schemeName, x.accountNumber,
                    x.girlName, x.depositBalance, x.branchLocation, x.ifscCode,
                    x.address, x.deleted, x.bankId
                )
            }
            return dto
        } else
            return emptyList()
    }

    // find Account By accountId
    @QueryHandler(queryName = "findGirlsSchemeById")
    fun findGirlsSchemeAccountById(girlsSchemeId: String): GirlsSchemeDto {
        val result = repository.findById(girlsSchemeId)
        if (result.isPresent && result.get().deleted == false) {
            return GirlsSchemeConverter.convertModelToDTO(result.get())
        }
        return GirlsSchemeDto()
    }
}