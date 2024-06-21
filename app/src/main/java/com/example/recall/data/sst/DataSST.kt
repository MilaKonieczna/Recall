package com.example.recall.data.sst

data class DataSST(
    val noTests: Int,
    val spanTime: Float,
    val totalCorrect: Int,
    val totalErrors: Int,
    val errorSequence: Int,
    val errorSquare: Int
)
