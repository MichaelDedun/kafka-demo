package com.dedun.kafkademo.service.iml

import com.dedun.kafkademo.model.Message
import com.dedun.kafkademo.service.IMessageService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl : IMessageService {
    override fun getAll(): List<Message> {
        TODO("Not yet implemented")
    }

    override fun getById(): Message? {
        TODO("Not yet implemented")
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }


}