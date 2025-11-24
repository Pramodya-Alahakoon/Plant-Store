package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmailVerificationActivity : AppCompatActivity() {

    private lateinit var otpDigit1: EditText
    private lateinit var otpDigit2: EditText
    private lateinit var otpDigit3: EditText
    private lateinit var otpDigit4: EditText
    private lateinit var otpDigit5: EditText
    private lateinit var verifyButton: Button
    private lateinit var resendOtpLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verification)

        otpDigit1 = findViewById(R.id.otpDigit1)
        otpDigit2 = findViewById(R.id.otpDigit2)
        otpDigit3 = findViewById(R.id.otpDigit3)
        otpDigit4 = findViewById(R.id.otpDigit4)
        otpDigit5 = findViewById(R.id.otpDigit5)
        verifyButton = findViewById(R.id.verifyButton)
        resendOtpLink = findViewById(R.id.resendOtpLink)

        val otpTextWatchers = listOf(otpDigit1, otpDigit2, otpDigit3, otpDigit4, otpDigit5)
        otpTextWatchers.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (otpDigit1.text.isNotEmpty() && otpDigit2.text.isNotEmpty() && otpDigit3.text.isNotEmpty() && otpDigit4.text.isNotEmpty() && otpDigit5.text.isNotEmpty()) {
                        verifyButton.visibility = Button.VISIBLE
                    } else {
                        verifyButton.visibility = Button.GONE
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        verifyButton.setOnClickListener {
            Toast.makeText(this, "Verified successfully", Toast.LENGTH_SHORT).show()
        }

        resendOtpLink.setOnClickListener {
            val intent = Intent(this, VerifyEmailActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}