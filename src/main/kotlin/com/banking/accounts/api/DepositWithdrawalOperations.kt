package com.banking.accounts.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DepositMoneyCommand(@TargetAggregateIdentifier var accountId: String, var amount: Double)

data class MoneyDepositedEvent(var accountId: String, var amount: Double)

data class WithdrawMoneyCommand(@TargetAggregateIdentifier var accountId: String, var amount: Double)

data class MoneyWithdrawnEvent(var accountId: String, var amount: Double)


data class UpdateAmountDto(var accountId: String, var amount: Double)



//data class  UpdateDepositAmountCommand(
//    @TargetAggregateIdentifier
//    var accountId: String?= null,
//    var balance: Double?= 0.0
//)
//data class UpdateDepositAmountEvent(
//    var accountId: String? = null,
//    var balance: Double? = 0.0
//)

