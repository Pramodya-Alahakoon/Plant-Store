package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShippingPaymentInfoActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var cardNumberEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var cvvEditText: EditText
    private lateinit var proceedToCheckoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_payment_info)

        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        emailEditText = findViewById(R.id.emailEditText)
        addressEditText = findViewById(R.id.addressEditText)
        cardNumberEditText = findViewById(R.id.cardNumberEditText)
        expiryDateEditText = findViewById(R.id.expiryDateEditText)
        cvvEditText = findViewById(R.id.cvvEditText)
        proceedToCheckoutButton = findViewById(R.id.proceedToCheckoutButton)

        proceedToCheckoutButton.setOnClickListener {
            if (validateInputs()) {
                Toast.makeText(this, "Proceeding to Checkout", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CheckoutActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all the details correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (nameEditText.text.isEmpty()) {
            nameEditText.error = "Name is required"
            isValid = false
        }

        if (phoneEditText.text.isEmpty() || !Patterns.PHONE.matcher(phoneEditText.text).matches()) {
            phoneEditText.error = "Valid phone number is required"
            isValid = false
        }

        if (emailEditText.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailEditText.text).matches()) {
            emailEditText.error = "Valid email is required"
            isValid = false
        }

        if (addressEditText.text.isEmpty()) {
            addressEditText.error = "Delivery address is required"
            isValid = false
        }

        if (cardNumberEditText.text.isEmpty()) {
            cardNumberEditText.error = "Card number is required"
            isValid = false
        }

        if (expiryDateEditText.text.isEmpty()) {
            expiryDateEditText.error = "Expiry date is required"
            isValid = false
        }

        if (cvvEditText.text.isEmpty()) {
            cvvEditText.error = "CVV is required"
            isValid = false
        }

        return isValid
    }
}