package com.hrv.mart.orderlibrary.model.query

import com.hrv.mart.orderlibrary.model.Status
import org.springframework.data.domain.Sort

data class OrderQuery (
    val status: List<Status> = listOf(
        Status.SHIPPED,
        Status.PROCESS,
        Status.PLACED,
        Status.CANCELLED
    ),
    val startingDate: OrderDate = OrderDate.getMinDate(),
    val endingDate: OrderDate = OrderDate.getMaxDate(),
    val sortInDecreasingDateOrder: Boolean = true
) {
    fun getSortingOrder() =
        if (this.sortInDecreasingDateOrder)
            Sort.Direction.DESC
        else
            Sort.Direction.ASC
}