package com.example.recall.dst

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.R
import com.example.recall.dst.DataDST
import java.util.*

class DST : AppCompatActivity() {

    private var noTests = 0
    private var spanTime: Float = 0f
    private var totalCorrectForward = 0
    private var totalCorrectBackward = 0
    private val forwardSpans = mutableListOf<Int>()
    private val backwardSpans = mutableListOf<Int>()
    private lateinit var sequenceTextView: TextView
    private lateinit var userInputEditText: EditText
    private lateinit var startTestButton: Button
    private lateinit var resultsTextView: TextView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dst)

        sequenceTextView = findViewById(R.id.sequenceTextView)
        userInputEditText = findViewById(R.id.userInputEditText)
        startTestButton = findViewById(R.id.startTestButton)
        resultsTextView = findViewById(R.id.resultsTextView)

        startTestButton.setOnClickListener {
            noTests++
            runForwardTest()
        }
    }

    private fun runForwardTest() {
        userInputEditText.visibility = View.GONE
        startTestButton.isEnabled = false
        var sequenceLength = 3
        var failCount = 0

        val forwardTestRunnable = object : Runnable {
            override fun run() {
                if (failCount < 2) {
                    val sequence = generateSequence(sequenceLength)
                    displaySequence(sequence) {
                        userInputEditText.visibility = View.VISIBLE
                        val response = getUserInput("Enter the sequence:")
                        if (checkSequence(response, sequence)) {
                            totalCorrectForward++
                            failCount = 0
                            forwardSpans.add(sequenceLength)
                        } else {
                            failCount++
                        }
                        sequenceLength++
                        handler.postDelayed(this, 2000)
                    }
                } else {
                    runBackwardTest()
                }
            }
        }
        handler.post(forwardTestRunnable)
    }

    private fun runBackwardTest() {
        userInputEditText.visibility = View.GONE
        var sequenceLength = 2
        var failCount = 0

        val backwardTestRunnable = object : Runnable {
            override fun run() {
                if (failCount < 2) {
                    val sequence = generateSequence(sequenceLength)
                    displaySequence(sequence) {
                        userInputEditText.visibility = View.VISIBLE
                        val response = getUserInput("Enter the sequence in reverse:")
                        if (checkSequence(response, sequence.reversed())) {
                            totalCorrectBackward++
                            failCount = 0
                            backwardSpans.add(sequenceLength)
                        } else {
                            failCount++
                        }
                        sequenceLength++
                        handler.postDelayed(this, 2000)  // Delay for 2 seconds before next sequence
                    }
                } else {
                    showResults()
                    startTestButton.isEnabled = true
                }
            }
        }
        handler.post(backwardTestRunnable)
    }

    private fun generateSequence(length: Int): String {
        return (1..length).joinToString("") { (0..9).random().toString() }
    }

    private fun displaySequence(sequence: String, callback: () -> Unit) {
        sequenceTextView.text = ""
        sequence.forEachIndexed { index, char ->
            handler.postDelayed({
                sequenceTextView.text = sequenceTextView.text.toString() + char
                if (index == sequence.length - 1) {
                    handler.postDelayed({
                        sequenceTextView.text = ""
                        callback()
                    }, 1000)
                }
            }, 1000 * index.toLong())
        }
    }

    private fun getUserInput(prompt: String): String {
        return userInputEditText.text.toString()
    }

    private fun checkSequence(input: String, expected: String): Boolean {
        return input == expected
    }

    private fun showResults() {
        val data = DataDST(noTests, spanTime, totalCorrectForward + totalCorrectBackward, Date())
        resultsTextView.text = data.toString()
    }
}
