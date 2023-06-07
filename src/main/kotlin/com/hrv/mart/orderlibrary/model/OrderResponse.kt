package com.hrv.mart.orderlibrary.model

import com.hrv.mart.cartresponse.model.CartResponse
import com.hrv.mart.orderlibrary.model.order.Order
import com.hrv.mart.orderlibrary.model.order.ProductOrdered
import java.time.LocalDateTime

data class OrderResponse (
    val userId: String,
    val products: List<CartResponse>,
    val price: Long,
    val status: Status = Status.PROCESS,
    val dateTimeOfOrder: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun parseFrom (order: Order, productOrdered: List<ProductOrdered>) =
            OrderResponse(
                userId = order.userId,
                price = order.price,
                products = productOrdered
                    .map {
                         CartResponse(
                             productId = it.productId,
                             quantity = it.quantity
                         )
                    },
                status = order.status,
                dateTimeOfOrder = order.dateTimeOfOrder
            )
    }
}