package com.dedun.kafkademo.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig(
        @Value("\${bootstrap.servers}")
        private val server: String
) {

    @Bean
    fun producerFactory() : ProducerFactory<String, Any> {
        val conf: MutableMap<String, Any> = HashMap()
        conf[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = server
        conf[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        conf[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory(conf);
    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }

}