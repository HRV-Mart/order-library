package com.hrv.mart.orderlibrary.model

import com.hrv.mart.cartresponse.model.CartResponse
import com.hrv.mart.orderlibrary.model.order.Order
import com.hrv.mart.orderlibrary.model.order.ProductOrdered
import java.time.LocalDateTime

data class OrderResponse (
    val orderId: String,
    val userId: String,
    val products: List<CartResponse>,
    val price: Long,
    val status: Status = Status.PROCESS,
    val dateTimeOfOrder: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        fun parseFrom (order: Order, productOrdered: List<ProductOrdered>) =
            OrderResponse(
                orderId = order.orderId,
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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderResponse

        if (orderId != other.orderId) return false
        if (userId != other.userId) return false
        if (products != other.products) return false
        if (price != other.price) return false
        return status == other.status
    }

    override fun hashCode(): Int {
        var result = orderId.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + products.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}