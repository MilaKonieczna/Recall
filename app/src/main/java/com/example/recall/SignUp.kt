package com.example.recall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    //TODO: Connect to database
    private var email: EditText? = null
    private var name: EditText? = null
    private var surname: EditText? = null
    private  var age: String? = null
    private  var nationality: String? = null
    private  var education: String? = null
    private var password: EditText? = null
    private var repeat: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        age = intent.getStringExtra("AGE")
        nationality = intent.getStringExtra("NATIONALITY")
        education = intent.getStringExtra("EDUCATION")

        val register: Button = findViewById(R.id.save)

        email = findViewById(R.id.emailEV)
        name = findViewById(R.id.nameEV)
        surname = findViewById(R.id.surnameEV)
        password = findViewById(R.id.passwordEV)
        repeat = findViewById(R.id.repeatEV)

        val logTv = findViewById<TextView>(R.id.LogTV)
        logTv.setOnClickListener{
            goToLogin()
        }

        register.setOnClickListener {
            if (validate()) goToLogin()

        }
    }

    private fun validate(): Boolean {
        if (name?.text.isNullOrBlank()) {
            Toast.makeText(this, "Username is required",Toast.LENGTH_SHORT).show()
            return false
        }
        if (surname?.text.isNullOrBlank()) {
            Toast.makeText(this, "Username is required",Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Provide valid email",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password?.text.isNullOrBlank()) {
            Toast.makeText(this, "Password is required",Toast.LENGTH_SHORT).show()
            return false
        }
        if (password!!.length() < 6) {
            Toast.makeText(this, "Password too short",Toast.LENGTH_SHORT).show()
            return false
        }
        var capital = false
        var number = false
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

        if (!capital) {
            Toast.makeText(this, "Password needs a Capital Letter",Toast.LENGTH_LONG).show()
            return false
        } else if (!number) {
            Toast.makeText(this, "Password needs a Number",Toast.LENGTH_LONG).show()
            return false
        }

        if (!password?.text?.toString().equals(repeat?.text?.toString())) {
            Toast.makeText(this, "The passwords aren't the same!",Toast.LENGTH_LONG).show()
            return false
        }
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        return true
    }

    private fun goToLogin() {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
    }
}
