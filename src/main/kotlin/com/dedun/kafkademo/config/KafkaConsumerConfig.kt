package com.dedun.kafkademo.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties

@Configuration
class KafkaConsumerConfig(
        @Value("\${bootstrap.servers}")
        private val server: String

) {

    @Bean
    fun consumerFactory() : ConsumerFactory<String, Any> {
        val conf: MutableMap<String, Any> = HashMap()
        conf[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = server
        conf[ConsumerConfig.GROUP_ID_CONFIG] = "demo"
        conf[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        conf[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        conf[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        conf[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
        return DefaultKafkaConsumerFactory(conf)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}