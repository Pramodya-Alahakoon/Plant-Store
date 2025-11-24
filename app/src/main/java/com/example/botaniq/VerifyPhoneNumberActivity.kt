package com.example.botaniq

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyPhoneNumberActivity : AppCompatActivity() {

    private lateinit var phoneNumberEditText: EditText
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone_number)

        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        confirmButton = findViewById(R.id.confirmButton)

        confirmButton.setOnClickListener {
            val enteredPhoneNumber = phoneNumberEditText.text.toString()
            val hardcodedPhoneNumber = "71123456"

            if (enteredPhoneNumber == hardcodedPhoneNumber) {
                Toast.makeText(this, "Code sent to your phone number", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "The phone number is incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}