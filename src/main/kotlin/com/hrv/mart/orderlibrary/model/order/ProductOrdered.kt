package com.hrv.mart.orderlibrary.model.order

import com.hrv.mart.orderlibrary.model.OrderRequest
import org.springframework.data.mongodb.core.mapping.Document

@Document("ProductOrdered")
data class ProductOrdered (
    val orderId: String,
    val productId: String,
    val quantity: Long,
    val userId: String
) {
    companion object {
        fun parseFrom(orderRequest: OrderRequest, order: Order) =
            orderRequest
                .products
                .map {cartResponse ->
                    ProductOrdered(
                        userId = order.userId,
                        productId = cartResponse.productId,
                        quantity = cartResponse.quantity,
                        orderId = order.orderId
                    )
                }
    }
}