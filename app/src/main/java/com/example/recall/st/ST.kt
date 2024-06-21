package com.example.recall.st

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R

class ST : AppCompatActivity() {

    private lateinit var textWord: TextView
    private lateinit var buttonRed: Button
    private lateinit var buttonGreen: Button
    private lateinit var buttonBlue: Button
    private lateinit var buttonYellow: Button

    private val colors = listOf("Red", "Green", "Blue", "Yellow")
    private val colorValues = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)
    private val practiceTrials = 5
    private val totalTrials = 40

    private var trialCount = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private var startTime = 0L
    private val results = mutableListOf<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st)

        textWord = findViewById(R.id.text_word)
        buttonRed = findViewById(R.id.button_red)
        buttonGreen = findViewById(R.id.button_green)
        buttonBlue = findViewById(R.id.button_blue)
        buttonYellow = findViewById(R.id.button_yellow)

        val buttons = listOf(buttonRed, buttonGreen, buttonBlue, buttonYellow)

        buttons.forEach { button ->
            button.setOnClickListener {
                handleButtonClick(button)
            }
        }

        startPracticeTrials()
    }

    private fun startPracticeTrials() {
        trialCount = 0
        startNextTrial()
    }

    private fun startNextTrial() {
        if (trialCount >= practiceTrials + totalTrials) {
            showResults()
            return
        }

        val word = colors.random()
        val color = colorValues.random()
        textWord.text = word
        textWord.setTextColor(color)

        if (trialCount >= practiceTrials) {
            startTime = SystemClock.elapsedRealtime()
        }

        trialCount++
    }

    private fun handleButtonClick(button: Button) {
        if (trialCount <= practiceTrials) {
            startNextTrial()
            return
        }

        val elapsedTime = SystemClock.elapsedRealtime() - startTime
        results.add(elapsedTime)

        val selectedColor = when (button.id) {
            R.id.button_red -> Color.RED
            R.id.button_green -> Color.GREEN
            R.id.button_blue -> Color.BLUE
            R.id.button_yellow -> Color.YELLOW
            else -> 0
        }

        if (selectedColor == textWord.currentTextColor) {
            correctAnswers++
        } else {
            incorrectAnswers++
        }

        startNextTrial()
    }

    private fun showResults() {
        val averageTime = if (results.isNotEmpty()) results.average() else 0.0
        val totalSequences = totalTrials
        val correctSequences = correctAnswers
        val incorrectSequences = incorrectAnswers

        val intent = Intent(this, ScoreST::class.java).apply {
            putExtra("averageTime", averageTime)
            putExtra("totalSequences", totalSequences)
            putExtra("correctSequences", correctSequences)
            putExtra("incorrectSequences", incorrectSequences)
            putExtra("correctAnswers", correctAnswers)
            putExtra("incorrectAnswers", incorrectAnswers)
        }
        startActivity(intent)
    }
}
