package com.banking.accounts.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteAccountCommand(@TargetAggregateIdentifier val accountId: String? = null)

data class AccountDeletedEvent(val accountId: String? = null)

data class DeleteAccountDTO(val accountId: String? = null)