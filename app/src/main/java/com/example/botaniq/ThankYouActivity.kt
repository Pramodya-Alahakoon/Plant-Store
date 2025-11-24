package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThankYouActivity : AppCompatActivity() {

    private lateinit var viewOrdersButton: Button
    private lateinit var continueShoppingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)

        viewOrdersButton = findViewById(R.id.viewOrdersButton)
        continueShoppingButton = findViewById(R.id.continueShoppingButton)

        viewOrdersButton.setOnClickListener {
            val intent = Intent(this, OrderHistoryActivity::class.java)
            startActivity(intent)
        }

        continueShoppingButton.setOnClickListener {
            val intent = Intent(this, AddReviewActivity::class.java)
            startActivity(intent)
        }
    }
}