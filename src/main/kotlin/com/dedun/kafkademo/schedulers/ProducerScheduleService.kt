package com.dedun.kafkademo.schedulers

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ProducerScheduleService(
        @Value("\${topic.json}")
        private val topic: String,
        private val kafkaTemplate: KafkaTemplate<String, Any>
) {


    @Scheduled(fixedRate = 5000)
    fun produceMsg() {
        val dispatchTime: String = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("hh:mm:ss"))
        val message: Message<String> = MessageBuilder
                .withPayload("msg time $dispatchTime")
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build()
        println("request $message")
        kafkaTemplate.send(message)
        println("request is send")
    }
}