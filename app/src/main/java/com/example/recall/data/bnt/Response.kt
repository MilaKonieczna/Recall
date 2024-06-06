package com.example.recall.data.bnt

data class Response(
    val objectId: Int,
    val responseText: String,
    val responseTime: Long,
    val cueUsed: Boolean = false
)
