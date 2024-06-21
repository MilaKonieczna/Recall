package com.example.recall.bnt

data class Response(
    val objectId: Int,
    val responseText: String,
    val responseTime: Long,
    val cueUsed: Int,
)
