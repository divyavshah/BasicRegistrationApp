package com.example.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var btnGoogle: Button
    private lateinit var btn: TextView
    private lateinit var btnLogin: Button

    private lateinit var inputPassword: EditText
    private lateinit var inputEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnGoogle = findViewById(R.id.btnGoogle)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)
        btnGoogle.setOnClickListener { signIn() }

        btn = findViewById(R.id.textViewSignUp)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnlogin)

        btnLogin.setOnClickListener { checkCredentials() }

        btn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun signIn() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                show()
            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun show() {
        Toast.makeText(applicationContext, "Signed in through google", Toast.LENGTH_SHORT).show()
    }

    private fun checkCredentials() {
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()

        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Not valid Email!")
        } else if (password.isEmpty() || password.length < 8) {
            showError(inputPassword, "Password must be of 8 characters or more!")
        } else {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showError(input: EditText, s: String) {
        input.error = s
        input.requestFocus()
    }
}

