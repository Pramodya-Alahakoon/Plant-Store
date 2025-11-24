package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    private lateinit var plantImageView: ImageView
    private lateinit var plantNameTextView: TextView
    private lateinit var plantPriceTextView: TextView
    private lateinit var quantityTextView: TextView
    private lateinit var subtotalTextView: TextView
    private lateinit var deliveryCostTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var cardNumberTextView: TextView
    private lateinit var expiryDateTextView: TextView
    private lateinit var cvvTextView: TextView
    private lateinit var confirmOrderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        plantImageView = findViewById(R.id.plantImageView)
        plantNameTextView = findViewById(R.id.plantNameTextView)
        plantPriceTextView = findViewById(R.id.plantPriceTextView)
        quantityTextView = findViewById(R.id.quantityTextView)
        subtotalTextView = findViewById(R.id.subtotalTextView)
        deliveryCostTextView = findViewById(R.id.deliveryCostTextView)
        totalTextView = findViewById(R.id.totalTextView)
        nameTextView = findViewById(R.id.nameTextView)
        phoneTextView = findViewById(R.id.phoneTextView)
        emailTextView = findViewById(R.id.emailTextView)
        addressTextView = findViewById(R.id.addressTextView)
        cardNumberTextView = findViewById(R.id.cardNumberTextView)
        expiryDateTextView = findViewById(R.id.expiryDateTextView)
        cvvTextView = findViewById(R.id.cvvTextView)
        confirmOrderButton = findViewById(R.id.confirmOrderButton)

        // Assuming these values are passed from the previous activities
        val plantName = intent.getStringExtra("plantName") ?: "Orchid"
        val plantPrice = intent.getDoubleExtra("plantPrice", 50.0)
        val quantity = intent.getIntExtra("quantity", 1)
        val subtotal = plantPrice * quantity
        val deliveryCost = 200.0
        val total = subtotal + deliveryCost

        val name = intent.getStringExtra("name") ?: "Name : User "
        val phone = intent.getStringExtra("phone") ?: "Phone Number : 071123456"
        val email = intent.getStringExtra("email") ?: "Email : user@gmail.com"
        val address = intent.getStringExtra("address") ?: "Delivery Address : Matara, Srilanka"
        val cardNumber = intent.getStringExtra("cardNumber") ?: "Card Number"
        val expiryDate = intent.getStringExtra("expiryDate") ?: "MM/YY"
        val cvv = intent.getStringExtra("cvv") ?: "CVV"

        plantImageView.setImageResource(R.drawable.orchids) // Replace with actual image resource
        plantNameTextView.text = plantName
        plantPriceTextView.text = "Rs :${plantPrice * quantity}"
        quantityTextView.text = quantity.toString()
        subtotalTextView.text = "Rs :${subtotal}"
        deliveryCostTextView.text = "Rs :${deliveryCost}"
        totalTextView.text = "Rs :${total}"

        nameTextView.text = name
        phoneTextView.text = phone
        emailTextView.text = email
        addressTextView.text = address
        cardNumberTextView.text = cardNumber
        expiryDateTextView.text = expiryDate
        cvvTextView.text = cvv

        confirmOrderButton.setOnClickListener {
            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
        }
    }
}