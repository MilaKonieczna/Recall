package com.example.recall

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Questionnaire : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val ageEditText: EditText = findViewById(R.id.ageEV)

        val nationalitySpinner: Spinner = findViewById(R.id.nationality_spinner)
        val countries = resources.getStringArray(R.array.countries_array)
        val nationalityAdapter = ArrayAdapter(this, R.layout.spinner_item, countries)
        nationalityAdapter.setDropDownViewResource(R.layout.spinner_item)
        nationalitySpinner.adapter = nationalityAdapter

        val educationSpinner: Spinner = findViewById(R.id.education_spinner)
        val educationLevels = resources.getStringArray(R.array.education_levels_array)
        val educationAdapter = ArrayAdapter(this, R.layout.spinner_item, educationLevels)
        educationAdapter.setDropDownViewResource(R.layout.spinner_item)
        educationSpinner.adapter = educationAdapter

        val saveButton: Button = findViewById(R.id.save)
        saveButton.setOnClickListener {
            val age = ageEditText.text.toString()
            val nationality = nationalitySpinner.selectedItem.toString()
            val education = educationSpinner.selectedItem.toString()

            // Send data to SignUp activity
            val intent = Intent(this, SignUp::class.java).apply {
                putExtra("AGE", age)
                putExtra("NATIONALITY", nationality)
                putExtra("EDUCATION", education)
            }
            startActivity(intent)
        }
    }
}
