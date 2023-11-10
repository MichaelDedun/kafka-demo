package com.dedun.kafkademo.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class MessageController(
        @Value("\${topic.json}")
        private val topic: String,
        private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @GetMapping
    fun demo(): ResponseEntity<Any> {
        val message : Message<String> = MessageBuilder
                .withPayload("test")
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader("X-Custom-Header", "Custom header here")
                .build()
        println("Message request $message")
        val send = kafkaTemplate.send(message)
        send.completable()
        return ResponseEntity.ok("SUCCESS")
    }

}