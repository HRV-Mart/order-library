package com.hrv.mart.orderlibrary.repository

import com.hrv.mart.apicall.APICaller
import com.hrv.mart.custompageable.model.Pageable
import com.hrv.mart.custompageable.model.QueryParams
import com.hrv.mart.orderlibrary.model.OrderResponse
import com.hrv.mart.orderlibrary.model.query.OrderQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Repository

@Repository
class OrderRepository (
    @Value("\${hrv.mart.orderUrl}")
    private val orderUrl: String,
    @Autowired
    private val apiCaller: APICaller
)
{
    fun getUserOrder(userId: String, queryParams: QueryParams, response: ServerHttpResponse) =
        apiCaller
            .getData(
                "${orderUrl}/${userId}${queryParams.getQueryParamForURL()}",
                response = response,
                responseClassType = Pageable::class.java
            )
            .map {
                it as OrderResponse
            }
    fun getOrderByUserIDAndOrderId(userId: String, orderId: String, response: ServerHttpResponse) =
        apiCaller
            .getData(
                "${orderUrl}/${userId}/${orderId}",
                response = response,
                responseClassType = OrderResponse::class.java
            )
    fun getOrderByApplyingFilter(
        orderQueryParams: OrderQuery,
        queryParam: QueryParams,
        response: ServerHttpResponse
    ) =
        apiCaller
            .postRequest(
                orderUrl,
                Pageable::class.java,
                orderQueryParams::class.java,
                response
            )
}