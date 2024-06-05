package com.example.recall

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recall.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    //TODO: Connect to database
    private lateinit var binding: ActivityLogInBinding
    private var email: EditText? = null
    private var password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signUp: TextView = findViewById(R.id.SignUpTV)
        email = binding.emailEV
        password = binding.passwordEV

        signUp.setOnClickListener { goToQuestionnaire() }

        binding.save.setOnClickListener {
            if(validate())goHome()
        }
    }


    private fun validate(): Boolean {
        if (email?.text.toString().isEmpty() || password?.text.toString().isEmpty()) {
            Toast.makeText(this, "Fill Out Your Information", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun goToQuestionnaire() {
        val intent = Intent(this, Questionnaire::class.java)
        startActivity(intent)
        finish()
    }

    private fun goHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}
