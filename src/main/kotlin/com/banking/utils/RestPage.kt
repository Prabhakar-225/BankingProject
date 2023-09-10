package com.banking.utils

import java.time.LocalDate


open class RestPage(private val totalElements: Long, private val totalPages: Int) {
    fun isEmpty(): Boolean {
        return this.totalElements == 0.toLong()
    }
}

data class PageRequestParam(var pageSize: Int,
                            var pageNumber: Int,
                            var sortColumns: List<String>? = listOf(),
                            var searchString: String? = null,
                            var fromDate: LocalDate? = null,
                            var toDate: LocalDate? = null
        //  var category: List<String>? = emptyList()
)

data class ParkUnitPriceRequest(var pageSize: Int,
                                var pageNumber: Int,
                                var parkId: String? = null)


