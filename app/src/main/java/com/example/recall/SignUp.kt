package com.example.recall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    private var email: EditText? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var repeat: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val register: Button = findViewById(R.id.save)

        email = findViewById(R.id.emailEV)
        username = findViewById(R.id.usernameEV)
        password = findViewById(R.id.passwordEV)
        repeat = findViewById(R.id.repeatEV)

        // Goes to log in activity if user has an account
        val logTv = findViewById<TextView>(R.id.LogTV)
        logTv.setOnClickListener{
            goToLogin()
        }

        // Setting up click listener for register button
        register.setOnClickListener {
            if (validate()) goToLogin()

        }
    }

    private fun validate(): Boolean {
        if (username?.text.isNullOrBlank()) {
            Toast.makeText(this, "Name is required",Toast.LENGTH_SHORT).show()
            return false
        }
        if (email?.text.isNullOrBlank()) {
            Toast.makeText(this, "Email is required",Toast.LENGTH_SHORT).show()
            return false
        }
        var monkey= false
        for (char in email?.text.toString()){
            if(char == '@'){
                monkey = true
                break
            }
        }
        if (!monkey){
            Toast.makeText(this, "Provide valid email!",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password?.text.isNullOrBlank()) {
            Toast.makeText(this, "Password is required",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password!!.length() < 6) {
            Toast.makeText(this, "Your password has to be at least 6 characters long!",Toast.LENGTH_SHORT).show()
            return false
        }
        var capital = false
        var special = false
        var number = false
        val specialCharacters = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '`',
            '~', '{', '}', ':', ';', '\"','\'', '<', '>', '.', '?', '/')
        val numbers = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

        for (char in password?.text.toString()) {
            if (numbers.contains(char)) {
                number = true
                break
            }
        }
        for (char in password?.text.toString()) {
            if (char.isUpperCase()) {
                capital = true
                break
            }
        }
        for (char in password?.text.toString()) {
            if (specialCharacters.contains(char)) {
                special = true
                break
            }
        }
        if (!capital) {
            Toast.makeText(this, "Your password should have a capital letter!",Toast.LENGTH_LONG).show()
            return false
        } else if (!special) {
            Toast.makeText(this, "Password should have a special sign!",Toast.LENGTH_LONG).show()
            return false
        } else if (!number) {
            Toast.makeText(this, "Password should have a number!",Toast.LENGTH_LONG).show()
            return false
        }

        if (!password?.text?.toString().equals(repeat?.text?.toString())) {
            Toast.makeText(this, "The passwords aren't the same!",Toast.LENGTH_LONG).show()
            return false
        }
        Toast.makeText(this, "Registration Success", Toast.LENGTH_LONG).show()
        return true
    }


    private fun goToLogin() {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
    }
}
