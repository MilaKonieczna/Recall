package com.example.recall.st

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R

class ScoreST : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_st)

        val textView: TextView = findViewById(R.id.text_results)

        val averageTime = intent.getDoubleExtra("averageTime", 0.0)
        val totalSequences = intent.getIntExtra("totalSequences", 0)
        val correctSequences = intent.getIntExtra("correctSequences", 0)
        val incorrectSequences = intent.getIntExtra("incorrectSequences", 0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val incorrectAnswers = intent.getIntExtra("incorrectAnswers", 0)

        textView.text = "Average Time: $averageTime ms\n" +
                "Total Sequences: $totalSequences\n" +
                "Correct Sequences: $correctSequences\n" +
                "Incorrect Sequences: $incorrectSequences\n" +
                "Correct Answers: $correctAnswers\n" +
                "Incorrect Answers: $incorrectAnswers"
    }
}
