package com.hrv.mart.orderlibrary.service

import com.hrv.mart.orderlibrary.model.OrderRequest
import com.hrv.mart.orderlibrary.model.OrderTopics
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class OrderProducer (
    @Autowired
    private val kafkaTemplate: ReactiveKafkaProducerTemplate<String, OrderRequest>
) {
    fun createOrder(order: OrderRequest) =
        sendMessage(order, OrderTopics.createOrderTopic)
    private fun <T : Any> sendMessage(data: T, topic: String) =
        kafkaTemplate.send(topic, MessageBuilder
            .withPayload(data)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build()
        )
}