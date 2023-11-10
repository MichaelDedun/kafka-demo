package com.dedun.kafkademo.service.listeners

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageListener(
        @Value("\${instance.id}")
        private val instanceId: String,
) {

    @KafkaListener(topics = ["\${topic.json}"], groupId = "demo")
    fun listener(request : ConsumerRecord<String, Any>) {
        println("Got request ${request.value()}")
        if (instanceId == "kafka-demo-lag") {
            Thread.sleep(3000)
        }
    }

}