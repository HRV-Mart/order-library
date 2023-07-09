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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (userId != other.userId) return false
        if (price != other.price) return false
        if (orderId != other.orderId) return false
        return status == other.status
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + orderId.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }

}