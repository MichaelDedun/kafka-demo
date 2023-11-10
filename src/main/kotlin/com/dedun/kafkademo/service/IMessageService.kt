package com.dedun.kafkademo.service

import com.dedun.kafkademo.model.Message

interface IMessageService {

    fun getAll() : List<Message>

    fun getById() : Message?

    fun save()

    fun delete()

    fun update()
}