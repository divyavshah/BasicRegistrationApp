package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var btn: TextView
    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputConformPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn = findViewById(R.id.Login)
        inputUsername = findViewById(R.id.inputUsername)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        inputConformPassword = findViewById(R.id.inputConformPassword)

        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setOnClickListener {
            checkCredentials()
        }

        btn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun checkCredentials() {
        val username = inputUsername.text.toString()
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()
        val conformPassword = inputConformPassword.text.toString()

        if (username.isEmpty() || username.length < 8) {
            showError(inputUsername, "Not valid username!")
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Not valid Email!")
        } else if (password.isEmpty() || password.length < 8) {
            showError(inputPassword, "Password must be of 8 characters or more!")
        } else if (conformPassword.isEmpty() || conformPassword != password) {
            showError(inputConformPassword, "Password does not match!")
        } else {
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showError(input: EditText, s: String) {
        input.error = s
        input.requestFocus()
    }
}

