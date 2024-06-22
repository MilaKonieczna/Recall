package com.example.recall.dst

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.bnt.StartBNT
import com.example.recall.main.Home

class ScoreDST : AppCompatActivity() {

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_dst)

        val spanTime = intent.getIntExtra("SpanTime", 0)
        val totalCorrect = intent.getIntExtra("totalCorrect", 0)
        val date = intent.getStringExtra("date")

        findViewById<TextView>(R.id.spantime).text = "Cue Utilization: $spanTime s"
        findViewById<TextView>(R.id.totalCorrect).text = "Total Correct: $totalCorrect"
        findViewById<TextView>(R.id.date).text = "Date: $date"

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, Home::class.java).apply {
                putExtra("fragmentToLoad", "StartDST")
            }
            startActivity(intent)
            finish()
        }

    }
}
