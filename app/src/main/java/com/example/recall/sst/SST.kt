package com.example.recall.sst

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import kotlin.random.Random

@Suppress("DEPRECATION")
class SST : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var startButton: Button
    private val sequence = mutableListOf<Int>()
    private var currentStep = 0
    private var score = 0
    private var isForward = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sst)

        gridLayout = findViewById(R.id.gridLayout)
        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            startButton.visibility = View.GONE
            startTest(true)
        }

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener {
                handleButtonPress(i + 1)
                button.alpha = 0.5f
                Handler().postDelayed({
                    button.alpha = 1.0f
                }, 200)
            }
        }
    }

    private fun startTest(forward: Boolean) {
        sequence.clear()
        currentStep = 0
        score = 0
        isForward = forward
        generateNextSequence()
    }

    private fun generateNextSequence() {
        sequence.add(Random.nextInt(1, 10))
        showSequence()
    }

    @SuppressLint("ResourceAsColor")
    private fun showSequence() {
        var delay: Long = 500
        sequence.forEachIndexed { _, value ->
            Handler().postDelayed({
                highlightButton(value, true)
            }, delay)
            delay += 1000
            Handler().postDelayed({
                highlightButton(value, false)
            }, delay)
            delay += 500
        }
        Handler().postDelayed({
            startListening()
        }, delay)
    }

    private fun highlightButton(number: Int, highlight: Boolean) {
        val button = gridLayout.getChildAt(number - 1) as Button
        button.setBackgroundColor(if (highlight) getColor(R.color.UIOnly) else getColor(R.color.white))
    }

    private fun startListening() {
        currentStep = 0
    }

    private fun handleButtonPress(number: Int) {
        if (sequence.isEmpty() || currentStep >= sequence.size) return

        val expected = if (isForward) sequence[currentStep] else sequence[sequence.size - 1 - currentStep]
        if (number == expected) {
            currentStep++
            if (currentStep == sequence.size) {
                score++
                generateNextSequence()
            }
        } else {
            val intent = Intent(this, ScoreSST::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
        }
    }
}
