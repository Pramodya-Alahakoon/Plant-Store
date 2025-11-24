package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val sendButton: Button = findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            val email = emailEditText.text.toString()

            if (isValidEmail(email)) {
                // Show email sent message and navigate to EmailSentActivity
                Toast.makeText(this, "Email has been sent", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, EmailSentActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Show error message
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}