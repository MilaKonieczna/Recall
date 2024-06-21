package com.example.recall.bnt

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R

class ScoreBNT : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_bnt)

        val totalCorrect = intent.getIntExtra("totalCorrect", 0)
        val totalErrors = intent.getIntExtra("totalErrors", 0)
        val phoneticErrors = intent.getIntExtra("phoneticErrors", 0)
        val semanticErrors = intent.getIntExtra("semanticErrors", 0)
        val perseverativeErrors = intent.getIntExtra("perseverativeErrors", 0)
        val cueUtilization = intent.getIntExtra("cueUtilization", 0)
        val date = intent.getStringExtra("date")

        findViewById<TextView>(R.id.totalCorrectTextView).text = "Total Correct: $totalCorrect"
        findViewById<TextView>(R.id.totalErrorsTextView).text = "Total Errors: $totalErrors"
        findViewById<TextView>(R.id.phoneticErrorsTextView).text = "Phonetic Errors: $phoneticErrors"
        findViewById<TextView>(R.id.semanticErrorsTextView).text = "Semantic Errors: $semanticErrors"
        findViewById<TextView>(R.id.perseverativeErrorsTextView).text = "Perseverative Errors: $perseverativeErrors"
        findViewById<TextView>(R.id.cueUtilizationTextView).text = "Cue Utilization: $cueUtilization"
        findViewById<TextView>(R.id.dateTextView).text = "Date: $date"
    }
}
