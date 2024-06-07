package com.example.recall

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.data.bnt.DataBNT
import com.example.recall.data.bnt.DrawingObject
import com.example.recall.data.bnt.Response
import org.apache.commons.text.similarity.LevenshteinDistance
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BNT : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var patientResponse: EditText
    private lateinit var submit: Button
    private lateinit var clue: Button

    private val drawings = listOf(
        DrawingObject(1, "apple", R.drawable.apple),
        DrawingObject(2, "pencil", R.drawable.pencil)
        // TODO: M: add more drawings
    ).shuffled()

    private val cues = listOf(
        Pair("apple", "fruit"),
        Pair("pencil", "something to write with")
        // TODO: L: add more cues, or just sent me a list

    )

    private var currentDrawingIndex = 0
    private val responses = mutableListOf<Response>()
    private var startTime: Long = 0
    private var cuesUsed: Int = 0
    private var cueCorrectCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bnt)

        image = findViewById(R.id.image)
        patientResponse = findViewById(R.id.response)
        submit = findViewById(R.id.submit)
        clue = findViewById(R.id.clue)

        loadNextDrawing()

        submit.setOnClickListener {
            submitResponse()
        }

        clue.setOnClickListener {
            provideClue()
        }
    }

    private fun loadNextDrawing() {
        if (currentDrawingIndex < drawings.size) {
            val currentDrawing = drawings[currentDrawingIndex]
            image.setImageResource(currentDrawing.imageResId)
            patientResponse.text.clear()
            startTime = SystemClock.elapsedRealtime()
        } else {
            calculateScore()
        }
    }

    private fun submitResponse() {
        val responseText = patientResponse.text.toString().trim()
        if (responseText.isNotEmpty()) {
            val responseTime = SystemClock.elapsedRealtime() - startTime
            val response = Response(
                objectId = drawings[currentDrawingIndex].id,
                responseText = responseText,
                responseTime = responseTime,
                cueUsed = cuesUsed
            )
            responses.add(response)

            if (cuesUsed > 0 && responseText.equals(drawings[currentDrawingIndex].name, ignoreCase = true)) {
                cueCorrectCount++
            }
            currentDrawingIndex++
            loadNextDrawing()
        } else {
            Toast.makeText(this, "Please enter a response", Toast.LENGTH_SHORT).show()
        }
    }

    private fun provideClue() {
        cuesUsed += 1
        val currentDrawing = drawings[currentDrawingIndex]
        val matchingClue = cues.find { it.first == currentDrawing.name }

        if (matchingClue != null) {
            Toast.makeText(this, matchingClue.second, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No clue available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateScore() {
        var totalCorrect = 0
        var phonemicErrors = 0
        var semanticErrors = 0
        var perseverativeErrors = 0
        val previousResponses = mutableSetOf<String>()

        responses.forEach { response ->
            val drawing = drawings.first { it.id == response.objectId }
            if (response.responseText.equals(drawing.name, ignoreCase = true)) {
                totalCorrect++
            } else {
                if (previousResponses.contains(response.responseText)) {
                    perseverativeErrors++
                } else {
                    if (isPhonemicError(response.responseText, drawing.name)) {
                        phonemicErrors++
                    } else if (isSemanticError(response.responseText, drawing.name)) {
                        semanticErrors++
                    }
                }
            }
            previousResponses.add(response.responseText)
        }

        val score = DataBNT(
            noTests = 1,
            totalCorrect = totalCorrect,
            totalErrors = phonemicErrors + semanticErrors + perseverativeErrors,
            phoneticErrors = phonemicErrors,
            semanticErrors = semanticErrors,
            perseverativeErrors = perseverativeErrors,
            cueUtilization = cueCorrectCount,
            date = getCurrentDate()
        )

        val intent = Intent(this, ScoreBNT::class.java).apply {
            putExtra("totalCorrect", totalCorrect)
            putExtra("totalErrors", phonemicErrors + semanticErrors + perseverativeErrors)
            putExtra("phoneticErrors", phonemicErrors)
            putExtra("semanticErrors", semanticErrors)
            putExtra("perseverativeErrors", perseverativeErrors)
            putExtra("cueUtilization", cueCorrectCount)
            putExtra("date", SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(score.date))
        }
        startActivity(intent)
    }

    // TODO: set number of tests from MongoDB


    private fun isPhonemicError(response: String, correct: String): Boolean {
        val threshold = 2
        val levenshteinDistance = LevenshteinDistance(threshold)
        val distance = levenshteinDistance.apply(response, correct)
        return distance != -1
    }

    private fun isSemanticError(response: String, correct: String): Boolean {
        val semanticMap = mapOf(
            "apple" to listOf("fruit", "food"),
            "pencil" to listOf("pen", "writing tool")
            //TODO: L: semantic map
        )
        return semanticMap[correct]?.contains(response) == true
    }

    private fun getCurrentDate(): Date {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        return dateFormat.parse(formattedDate) ?: currentDate
    }
}
