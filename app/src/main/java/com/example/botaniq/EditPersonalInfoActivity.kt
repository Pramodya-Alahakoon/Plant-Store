package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EditPersonalInfoActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var saveChangesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_personal_info)

        profileImageView = findViewById(R.id.profileImageView)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        locationEditText = findViewById(R.id.locationEditText)
        saveChangesButton = findViewById(R.id.saveChangesButton)

        // Set the hardcoded profile image
        profileImageView.setImageResource(R.drawable.ic_profile_placeholder)

        saveChangesButton.setOnClickListener {
            if (validateInputs()) {
                // Save changes and redirect to info changed page
                val intent = Intent(this, InfoChangedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (nameEditText.text.isEmpty()) {
            nameEditText.error = "Name is required"
            isValid = false
        }

        if (emailEditText.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches()) {
            emailEditText.error = "Valid email is required"
            isValid = false
        }

        if (phoneEditText.text.isEmpty() || !Patterns.PHONE.matcher(phoneEditText.text).matches()) {
            phoneEditText.error = "Valid phone number is required"
            isValid = false
        }

        if (locationEditText.text.isEmpty()) {
            locationEditText.error = "Location is required"
            isValid = false
        }

        return isValid
    }
}