package com.example.botaniq

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PromocodesActivity : AppCompatActivity() {

    private lateinit var voucherEditText: EditText
    private lateinit var addVoucherButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promocodes)

        voucherEditText = findViewById(R.id.voucherEditText)
        addVoucherButton = findViewById(R.id.addVoucherButton)

        addVoucherButton.setOnClickListener {
            val voucherCode = voucherEditText.text.toString()
            if (voucherCode.isNotEmpty()) {
                Toast.makeText(this, "Voucher added: $voucherCode", Toast.LENGTH_SHORT).show()
                // Handle the logic for adding the voucher here
            } else {
                Toast.makeText(this, "Please enter a voucher number", Toast.LENGTH_SHORT).show()
            }
        }
    }
}