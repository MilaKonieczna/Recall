package com.example.recall.sst

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.main.Home

class ScoreSST : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sst)

        val spanTime = intent.getLongExtra("SpanTime", 0)
        val totalCorrect = intent.getIntExtra("totalCorrect", 0)

        findViewById<TextView>(R.id.spantime).text = "SpanTime: $spanTime s"
        findViewById<TextView>(R.id.totalCorrect).text = "Total Correct: $totalCorrect"

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartSST")
            }
            startActivity(intent)
            finish()
        }
    }
}
