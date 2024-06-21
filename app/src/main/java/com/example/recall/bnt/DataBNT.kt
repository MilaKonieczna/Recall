package com.example.recall.bnt

import java.util.Date

data class DataBNT(
    val noTests: Int,
    val totalCorrect: Int,
    val totalErrors: Int,
    val phoneticErrors: Int,
    val semanticErrors: Int,
    val perseverativeErrors: Int,
    val cueUtilization: Int,
    val date: Date
)

