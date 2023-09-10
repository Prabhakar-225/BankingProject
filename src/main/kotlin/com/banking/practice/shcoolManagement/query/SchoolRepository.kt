package com.banking.practice.shcoolManagement.query

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SchoolRepository: MongoRepository<SchoolState, String> {
}