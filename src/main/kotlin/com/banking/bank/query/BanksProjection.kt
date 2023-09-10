package com.banking.bank.query


import com.banking.accounts.api.AccountCreatedEvent
import com.banking.accounts.query.AccountRepository
import com.banking.accounts.query.AccountState
import com.banking.bank.api.*
import com.banking.bank.util.BankConverter
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class BanksProjection(
    private val repository: BanksRepository,
    private val acRepository: AccountRepository,
) {

    //create Bank
    @EventHandler
    fun createBankEvent(event: BankCreatedEvent) {
        val bank = BankState()
        // val accounts = this.repository.findAllByIds(event.accountDetails?.map { x -> x.accountId }).toList()

        bank.bankId = event.bankId
        bank.bankName = event.bankName
        bank.accountDetails = event.accountDetails?.map { r ->
            Accounts(
                r.accountId, r.accountType, r.accountNumber, r.customerName,
                r.balance, r.branchLocation, r.deleted
            )
        }!!.toList()
        bank.bankLocation = event.bankLocation
        bank.accounts = event.accounts
        bank.bankHeadOffice = event.bankHeadOffice
        bank.bankDeleted = event.bankDeleted

        this.repository.save(bank)
    }


    //update Bank
    @EventHandler
    fun updateBankEvent(updatedEvent: BankUpdatedEvent) {
        val bank = repository.findById(updatedEvent.bankId!!)
        if (bank.isPresent) {
            bank.get().bankName = updatedEvent.bankName
            bank.get().accountDetails = updatedEvent.accountDetails?.map { r ->
                Accounts(
                    r.accountId, r.accountType, r.accountNumber, r.customerName,
                    r.balance, r.branchLocation, r.deleted
                )
            }
            bank.get().bankLocation = updatedEvent.bankLocation
            bank.get().accounts = updatedEvent.accounts
            bank.get().bankHeadOffice = updatedEvent.bankHeadOffice
            bank.get().bankDeleted = updatedEvent.bankDeleted
            this.repository.save(bank.get())
        }
    }

    // Soft core deleted Bank
    @EventHandler
    fun deleteBankTemporary(event: BankDeletedEvent) {
        val bank = repository.findById(event.bankId!!)
        if (bank.isPresent) {
            bank.get().bankDeleted = true
            this.repository.save(bank.get())
        }
    }

    //delete Permanent
    @EventHandler
    fun deleteBankPermanently(event: BankPermanentDeletedEvent) {
        val bank = this.repository.findById(event.bankId!!)
        if (bank.isPresent) {
            this.repository.delete(bank.get())
        }
    }

    //find All Banks
    @QueryHandler(queryName = "findAllBanks")
    fun findAllBanks(): List<BankDto> {
        val banks = this.repository.findAll()
        if (banks.isNotEmpty()) {
            val dto = banks.filter { x -> x.bankDeleted == false }.map { x ->
                BankDto(
                    x.bankId, x.bankName, x.accountDetails?.map { r ->
                        Accounts(
                            r.accountId, r.accountType, r.accountNumber, r.customerName,
                            r.balance, r.branchLocation, r.deleted
                        )
                    }, x.bankLocation, x.ifscCode, x.accounts, x.bankHeadOffice, x.bankDeleted
                )
            }
            return dto
        } else
            return emptyList()
    }

    // find Bank By bankId
    @QueryHandler(queryName = "findByBankId")
    fun findByBankId(bankId: String): BankDto {
        val result = repository.findById(bankId)
        if (result.isPresent && result.get().bankDeleted == false) {
            return BankConverter.convertModelToDTO(result.get())
        }
        return BankDto()
    }

//    @QueryHandler(queryName = "findHandler")
//    fun findHandler(bankId: String): BankDto{
//        val result = repository.findById(bankId)
//        if(result.isPresent)
//            return BankConverter.convertModelToDTO(result.get())
//        return BankDto()
//    }

    @EventHandler
    fun saveAccount(event: AccountCreatedEvent) {
        val accountState = AccountState()

        accountState.accountId = event.accountId
        accountState.accountType = event.accountType
        accountState.accountNumber = event.accountNumber
        accountState.customerName = event.customerName
        accountState.balance = event.balance
        accountState.bankBranchLocation = event.bankBranchLocation
        accountState.deleted = event.deleted
        //  repository.save(accountState)
    }

     @EventHandler
     fun addAccount(event: AccountsToBankAddedEvent) {

         var bankOptional = this.repository.findById(event.bankId!!)
         if (bankOptional.isPresent) {
             val bankState = bankOptional.get()

             bankState.accountDetails = bankState.accountDetails?.plus(
                 Accounts(
                     event.accountId, event.accountType,
                     event.accountNumber, event.customerName, event.balance,
                     event.bankBranchLocation,event.deleted
                 )
             )
             this.repository.save(bankState)
             //  repository.save(accountState)
         }
     }


}