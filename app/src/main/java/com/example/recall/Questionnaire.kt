package com.example.recall

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Questionnaire : AppCompatActivity() {

    private lateinit var countries: Array<String>
    private lateinit var nationalitySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        val ageEditText: EditText = findViewById(R.id.ageEV)

        nationalitySpinner = findViewById(R.id.nationality_spinner)
        countries = resources.getStringArray(R.array.countries_array)

//        nationalitySpinner.setOnClickListener {
//           // showSearchableSpinnerDialog()
//        }

        val educationSpinner: Spinner = findViewById(R.id.education_spinner)
        val educationLevels = resources.getStringArray(R.array.education_levels_array)
        val educationAdapter = ArrayAdapter(this, R.layout.spinner_item, educationLevels)
        educationAdapter.setDropDownViewResource(R.layout.spinner_item)
        educationSpinner.adapter = educationAdapter


        val nationalityAdapter = ArrayAdapter(this, R.layout.spinner_item, countries)
        nationalityAdapter.setDropDownViewResource(R.layout.spinner_item)
        nationalitySpinner.adapter = nationalityAdapter


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

//    private fun showSearchableSpinnerDialog() {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.searchable_spinner)
//        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        dialog.show()
//
//        val searchEditText: EditText = dialog.findViewById(R.id.searchEditText)
//        val countriesListView: ListView = dialog.findViewById(R.id.countriesListView)
//
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countries)
//        countriesListView.adapter = adapter
//
//        searchEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                adapter.filter.filter(s)
//            }
//
//            override fun afterTextChanged(s: Editable) {}
//        })
//
//        countriesListView.setOnItemClickListener { parent, view, position, id ->
//            val selectedCountry = adapter.getItem(position)
//            val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, arrayOf(selectedCountry))
//            nationalitySpinner.adapter = spinnerAdapter
//            nationalitySpinner.setSelection(0)
//            dialog.dismiss()
//        }
//    }
}
