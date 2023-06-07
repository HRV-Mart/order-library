package com.hrv.mart.orderlibrary.model.order

import com.hrv.mart.orderlibrary.model.OrderRequest
import com.hrv.mart.orderlibrary.model.Status
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("Order")
data class Order (
    val userId: String,
    val price: Long,
    // Params with default values
    val orderId: String=ObjectId.get().toString(),
    val status: Status = Status.PROCESS,
    val dateTimeOfOrder: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun parseFrom(orderRequest: OrderRequest) =
            Order(
                userId=orderRequest.userId,
                price = orderRequest.price
            )
    }
}