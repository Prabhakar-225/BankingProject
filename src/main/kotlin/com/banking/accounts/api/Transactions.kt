package com.banking.accounts.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class MoneyTransactionCommand(@TargetAggregateIdentifier var accountIdSends: String,
                                   var sendAmount: Double, @TargetAggregateIdentifier var accountIdReceived: String)

data class MoneyTransferredEvent(var accountIdSends: String, var sendAmount: Double, var accountIdReceived: String)


data class UpdateTransactionAmountDto(var accountIdSends: String, var sendAmount: Double, var accountIdReceived: String)