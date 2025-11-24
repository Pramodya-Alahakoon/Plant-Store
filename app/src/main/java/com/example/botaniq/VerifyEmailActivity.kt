package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyEmailActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var sendConfirmationLinkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)

        emailEditText = findViewById(R.id.emailEditText)
        sendConfirmationLinkButton = findViewById(R.id.sendConfirmationLinkButton)

        sendConfirmationLinkButton.setOnClickListener {
            val enteredEmail = emailEditText.text.toString()
            val hardcodedEmail = "user@gmail.com"

            if (enteredEmail == hardcodedEmail) {
                Toast.makeText(this, "Email sent successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, EmailVerificationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "The email is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}