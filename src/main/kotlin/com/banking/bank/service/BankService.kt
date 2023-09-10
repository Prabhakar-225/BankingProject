package com.banking.bank.service


import com.banking.accounts.api.AccountDto
import com.banking.accounts.query.AccountRepository
import com.banking.accounts.query.AccountState
import com.banking.bank.api.*
import com.banking.bank.query.Accounts
import com.banking.bank.query.BanksRepository
import com.banking.configurations.ResponseWithError
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class BankService(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway,
    private val accountRepository: AccountRepository,
    private val repository: BanksRepository) {

    private val logger = LoggerFactory.getLogger(BankService::class.java)

    // to create account
    fun createBank(command: CreateBankCommand): CompletableFuture<ResponseWithError<String>> {


        val accountIds = command.accountDetails?.map { x -> x.accountId }!!.toList()

        val accounts = this.accountRepository.findAllById(accountIds).toList()

        if (accounts.isNotEmpty()) {

            var accountMap = emptyMap<String, AccountState>()

            if (accounts.isNotEmpty()) {
                accountMap = accounts.map { x -> x.accountId!! to x }.toMap()
            }

            command.accountDetails?.forEach { x ->
                x.accountType = accountMap.get(x.accountId)?.accountType
                x.accountNumber = accountMap.get(x.accountId)?.accountNumber
                x.customerName = accountMap.get(x.accountId)?.customerName
                x.balance = accountMap.get(x.accountId)?.balance
                x.branchLocation = accountMap.get(x.accountId)?.bankBranchLocation
                x.deleted = accountMap.get(x.accountId)?.deleted

            }
            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Created Bank SuccessFully ->" + command.bankId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Not Present"))
    }

    fun updateBank(command: UpdateBankCommand): CompletableFuture<ResponseWithError<String>>{

        val result = commandGateway.send<Any>(command).thenApply { r ->
            ResponseWithError.of("Bank Updated SuccessFully ->" + command.bankId)
        }.exceptionally { x ->
            logger.error(x.message)
            ResponseWithError.ofError(x.message)
        }
        return result
    }

    fun  deleteBank(command: DeleteBankCommand): CompletableFuture<ResponseWithError<String>>{
        val bank = this.repository.findById(command.bankId!!)
        if (bank.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Bank Delete with ID -> " + command.bankId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("BankId Not found"))
        }
    }

    fun deleteBankPermanent(command: PermanentDeleteBankCommand): CompletableFuture<ResponseWithError<String>>{
        val bank = this.repository.findById(command.bankId!!)
        if (bank.isPresent) {
            val result = commandGateway.send<Any>(command)
                .thenApply { r -> ResponseWithError.of("Bank Deleted with ID -> " + command.bankId) }
                .exceptionally { x ->
                    logger.error(x.message)
                    ResponseWithError.ofError(x.message)
                }
            return result
        } else {
            return CompletableFuture.completedFuture(ResponseWithError.ofError("BankId Not found"))
        }
    }

    fun findAllBanks():CompletableFuture<ResponseWithError<List<BankDto>>>{
        val result = queryGateway.query("findAllBanks", "",
            ResponseTypes.multipleInstancesOf(BankDto::class.java))
        return result.thenApply { x ->
            if (x.isEmpty()) ResponseWithError.ofError("Banks Not Found")
            else ResponseWithError.of(x)
        }.exceptionally { e -> ResponseWithError.ofError(e.message) }
    }


    fun findByBankId(bankId: String): CompletableFuture<ResponseWithError<BankDto>> {
        val bank = this.repository.findById(bankId)
        if(bank.isPresent) {
            val result = queryGateway.query(
                "findByBankId", bankId,
                ResponseTypes.instanceOf(BankDto::class.java)
            )

            return result.thenApply { x ->
                if (x.bankId == null) ResponseWithError.ofError("BankId null found")
                else if (x.bankDeleted == true) ResponseWithError.ofError("BankId found But Deleted")
                else ResponseWithError.of(x)
            }
                .exceptionally { e -> ResponseWithError.ofError(e.message) }
        }
        return CompletableFuture.completedFuture(ResponseWithError.ofError("Bank Id Not Found"))
    }

    fun addAccount(command: AddAccountsToBankCommand): CompletableFuture<ResponseWithError<String>> {

            val result = commandGateway.send<Any>(command).thenApply { r ->
                ResponseWithError.of("Add Account SuccessFully ->" + command.bankId)
            }.exceptionally { x ->
                logger.error(x.message)
                ResponseWithError.ofError(x.message)
            }
            return result
        }
    }
