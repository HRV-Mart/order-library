package com.hrv.mart.orderlibrary.model

import com.hrv.mart.cartresponse.model.CartResponse
import com.hrv.mart.orderlibrary.model.order.Order
import com.hrv.mart.orderlibrary.model.order.ProductOrdered


data class OrderRequest (
    val userId: String,
    val products: List<CartResponse>,
    val price: Long
) {
    fun getOrder() =
        Order.parseFrom(this)
    fun getProductOrdered(order: Order) =
        ProductOrdered.parseFrom(this, order)

}