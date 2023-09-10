package com.banking.fixedDepositAccounts.jointFamily.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class JointFamily(
    @Id
    var jointFamilyId: String? = null,
    var accountNumber: Long? = null,
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

data class PersonNames(
    var person1: String? = null,
    var person2: String? = null,
    var person3: String? = null,
)

data class PersonsPhoneNumbers(
    var person1: Number? =null,
    var person2: Number? =null,
    var person3: Number? =null
)
