package com.banking.fixedDepositAccounts.jointFamily.api

import com.banking.fixedDepositAccounts.jointFamily.query.PersonNames
import com.banking.fixedDepositAccounts.jointFamily.query.PersonsPhoneNumbers
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateJointFamilyCommand(
    @TargetAggregateIdentifier
    var jointFamilyId: String? = null,
    var schemeName: String? ="JointFamily Scheme",
    var jointFamilyName: String? = null,
    var jointFamilyAddress: String? = null,
    var personsToInvest: Number? =0,
    var personsNames: PersonNames? = null,
    var personsPhoneNumbers: PersonsPhoneNumbers? = null,
    var bankName: String? =null,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var depositMoney: Double? = null,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class JointFamilyUpdatedEvent(
    var jointFamilyId: String? = null,
    var schemeName: String? ="JointFamily Scheme",
    var jointFamilyName: String? = null,
    var jointFamilyAddress: String? = null,
    var personsToInvest: Number? =0,
    var personsNames: PersonNames? = null,
    var personsPhoneNumbers: PersonsPhoneNumbers? = null,
    var bankName: String? =null,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var depositMoney: Double? = null,
    var bankId: String? = null,
    var deleted: Boolean? = false
)

data class UpdateJointFamilyDTO(

    var jointFamilyId: String? = null,
    var schemeName: String? ="JointFamily Scheme",
    var jointFamilyName: String? = null,
    var jointFamilyAddress: String? = null,
    var personsToInvest: Number? =0,
    var personsNames: PersonNames? = null,
    var personsPhoneNumbers: PersonsPhoneNumbers? = null,
    var bankName: String? =null,
    var branchLocation: String? = null,
    var ifscCode: String? = null,
    var depositMoney: Double? = null,
    var bankId: String? = null,
    var deleted: Boolean? = false
)