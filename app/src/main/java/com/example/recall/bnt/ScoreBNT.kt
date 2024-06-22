package com.example.recall.bnt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Home

class ScoreBNT : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
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

        findViewById<TextView>(R.id.totalCorrect).text = "Total Correct: $totalCorrect"
        findViewById<TextView>(R.id.totalErrors).text = "Total Errors: $totalErrors"
        findViewById<TextView>(R.id.phoneticErrors).text = "Phonetic Errors: $phoneticErrors"
        findViewById<TextView>(R.id.semanticErrors).text = "Semantic Errors: $semanticErrors"
        findViewById<TextView>(R.id.perseverativeErrors).text = "Perseverative Errors: $perseverativeErrors"
        findViewById<TextView>(R.id.cueUtilization).text = "Cue Utilization: $cueUtilization"
        findViewById<TextView>(R.id.date).text = "Date: $date"

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartBNT")
            }
            startActivity(intent)
            finish()
        }
    }
}
