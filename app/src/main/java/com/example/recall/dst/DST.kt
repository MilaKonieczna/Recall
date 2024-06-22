package com.example.recall.dst

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import java.util.*

@Suppress("DEPRECATION")
class DST : AppCompatActivity() {

    private lateinit var sequenceTextView: TextView
    private lateinit var enterTextView: TextView
    private lateinit var numberButtons: List<Button>
    private lateinit var sequenceHandler: Handler

    private var currentSequence = mutableListOf<Int>()
    private var userInput = mutableListOf<Int>()
    private var totalCorrect = 0
    private var startTime = 0L
    private var sequenceLength = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dst)

        sequenceTextView = findViewById(R.id.sequenceTextView)
        enterTextView = findViewById(R.id.Enter)

        numberButtons = listOf(
            findViewById(R.id.one), findViewById(R.id.two), findViewById(R.id.three),
            findViewById(R.id.four), findViewById(R.id.five), findViewById(R.id.six),
            findViewById(R.id.seven), findViewById(R.id.eight), findViewById(R.id.nine)
        )

        sequenceHandler = Handler()

        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberButtonClick(it) }
        }

        startGame()
    }

    private fun startGame() {
        totalCorrect = 0
        sequenceHandler.postDelayed({ displayNextSequence() }, 1000)
        startTime = System.currentTimeMillis()
    }

    private fun displayNextSequence() {
        currentSequence = generateRandomSequence(sequenceLength)
        sequenceTextView.text = currentSequence.joinToString(" ")

        userInput.clear()
        enterTextView.text = ""

        numberButtons.forEach { it.isEnabled = true }
    }

    private fun generateRandomSequence(length: Int): MutableList<Int> {
        val randomSequence = mutableListOf<Int>()
        repeat(length) {
            randomSequence.add((1..9).random())
        }
        return randomSequence
    }

    private fun onNumberButtonClick(view: View) {
        val number = (view as Button).text.toString().toInt()
        userInput.add(number)
        enterTextView.text = userInput.joinToString(" ")

        if (userInput.size == currentSequence.size) {
            checkUserInput()
        }
    }

    private fun checkUserInput() {
        numberButtons.forEach { it.isEnabled = false }

        if (userInput == currentSequence) {
            totalCorrect++
            sequenceLength++
            sequenceHandler.postDelayed({ displayNextSequence() }, 1000)
        } else {
            endGame()
        }
    }

    private fun endGame() {
        val spanTime = (System.currentTimeMillis() - startTime) / 1000F
        val currentDate = Date()

        val intent = Intent(this, ScoreDST::class.java).apply {
            putExtra("spanTime", spanTime)
            putExtra("totalCorrect", totalCorrect)
            putExtra("date", currentDate)
        }
        startActivity(intent)
        finish()
    }
}
