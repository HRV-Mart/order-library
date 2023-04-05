package com.hrv.mart.orderlibrary.model

import com.hrv.mart.cartresponse.model.CartResponse


data class OrderRequest (
    val userId: String,
    val products: List<CartResponse>,
    val price: Long
) {
    fun getOrderResponse() =
        OrderResponse(
            userId=userId,
            products=products,
            price=price
        )
}