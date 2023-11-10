package com.dedun.kafkademo.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@EnableKafka
class KafkaConfig(
        @Value("\${bootstrap.servers}")
        private val server: String,
        @Value("\${topic.json}")
        private val topic: String
) {

    @Bean
    fun kafkaAdmin() : KafkaAdmin {
        val conf: MutableMap<String, Any> = HashMap()
        conf[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = server
        return KafkaAdmin(conf)
    }

    @Bean
    fun demoTopic() : NewTopic {
        return NewTopic(topic, 4, 1.toShort())
    }

}