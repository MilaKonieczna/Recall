package com.example.recall.st

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Home

class ScoreST : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_st)

        val averageTime = intent.getDoubleExtra("averageTime", 0.0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val incorrectAnswers = intent.getIntExtra("incorrectAnswers", 0)

        findViewById<TextView>(R.id.averageTime).text = "SpanTime: $averageTime s"
        findViewById<TextView>(R.id.correctAnswers).text = "Total Correct: $correctAnswers"
        findViewById<TextView>(R.id.incorrectAnswers).text = "Total Correct: $incorrectAnswers"
        findViewById<Button>(R.id.back).setOnClickListener {

            findViewById<Button>(R.id.back).setOnClickListener {
                val intent = Intent(this, Home::class.java).apply {
                    putExtra("fragmentToLoad", "StartST")
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
