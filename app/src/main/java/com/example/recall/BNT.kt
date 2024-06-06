package com.example.recall

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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BNT : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var patientResponse: EditText
    private lateinit var submit: Button

    private val drawings = listOf(
        DrawingObject(1, "apple", R.drawable.apple),
        DrawingObject(2, "pencil", R.drawable.pencil)
        //TODO: add cards
    ).shuffled()

    private var currentDrawingIndex = 0
    private val responses = mutableListOf<Response>()
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bnt)

        image = findViewById(R.id.image)
        patientResponse = findViewById(R.id.response)
        submit = findViewById(R.id.submit)

        loadNextDrawing()

        submit.setOnClickListener {
            submitResponse()
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
                responseTime = responseTime
            )
            responses.add(response)
            currentDrawingIndex++
            loadNextDrawing()
        } else {
            Toast.makeText(this, "Please enter a response", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateScore() {
        var totalCorrect = 0
        val phonemicErrors = 0
        val semanticErrors = 0
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
                    // TODO: Implement phonemic and semantic errors
                }
            }
            previousResponses.add(response.responseText)
        }

        val score = DataBNT(
            // TODO: MONGODB NUMBER OF TESTS
            // noTests = ,
            totalCorrect = totalCorrect,
            totalErrors = phonemicErrors + semanticErrors + perseverativeErrors,
            phoneticErrors = phonemicErrors,
            semanticErrors = semanticErrors,
            perseverativeErrors = perseverativeErrors,
            date = getCurrentDate()
        )
        //TODO:Display results

    }

    private fun getCurrentDate(): Date {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        return dateFormat.parse(formattedDate) ?: currentDate
    }
}
