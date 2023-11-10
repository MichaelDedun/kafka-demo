package com.dedun.kafkademo.model

data class Message(
        val id : Int = 0,
        val body : String,
        val topic : String
)
