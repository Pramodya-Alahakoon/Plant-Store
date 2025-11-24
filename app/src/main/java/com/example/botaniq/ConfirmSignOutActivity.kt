package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConfirmSignOutActivity : AppCompatActivity() {

    private lateinit var cancelButton: Button
    private lateinit var sureButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_sign_out)

        cancelButton = findViewById(R.id.cancelButton)
        sureButton = findViewById(R.id.sureButton)

        cancelButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        sureButton.setOnClickListener {
            // Clear all order details
            clearOrderDetails()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun clearOrderDetails() {
        // Code to clear all order details
        // For example, if using SharedPreferences:
        val sharedPreferences = getSharedPreferences("OrderDetails", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}