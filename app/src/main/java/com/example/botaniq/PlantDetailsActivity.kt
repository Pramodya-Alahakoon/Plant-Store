package com.example.botaniq

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.botaniq.models.AllPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class PlantDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)

        // Setup toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val plantImageView: ImageView = findViewById(R.id.plantImageView)
        val plantNameTextView: TextView = findViewById(R.id.plantNameTextView)
        val plantRatingBar: RatingBar = findViewById(R.id.plantRatingBar)
        val ratingText: TextView = findViewById(R.id.ratingText)
        val plantPriceTextView: TextView = findViewById(R.id.plantPriceTextView)
        val decreaseQuantityButton: ImageButton = findViewById(R.id.decreaseQuantityButton)
        val increaseQuantityButton: ImageButton = findViewById(R.id.increaseQuantityButton)
        val quantityTextView: TextView = findViewById(R.id.quantityTextView)
        val plantTypeText: TextView = findViewById(R.id.plantTypeText)
        val plantPotText: TextView = findViewById(R.id.plantPotText)
        val plantDescriptionTextView: TextView = findViewById(R.id.plantDescriptionTextView)
        val careLevelText: TextView = findViewById(R.id.careLevelText)
        val addToCartButton: MaterialButton = findViewById(R.id.addToCartButton)
        val buyNowButton: MaterialButton = findViewById(R.id.buyNowButton)


        // Get the plant data from the intent
        val plant = intent.getParcelableExtra<AllPlant>("plant")

        plant?.let {
            plantImageView.setImageResource(it.imageResource)
            plantNameTextView.text = it.name
            plantRatingBar.rating = it.rating.toFloat()
            ratingText.text = "(${it.rating})"
            plantPriceTextView.text = it.price
            plantTypeText.text = "Type: ${it.type}"
            plantPotText.text = "Pot: ${it.potType}"
            plantDescriptionTextView.text = it.description
            careLevelText.text = "Care Level: ${it.careLevel}"

            var quantity = 1
            quantityTextView.text = quantity.toString()

            decreaseQuantityButton.setOnClickListener {
                if (quantity > 1) {
                    quantity--
                    quantityTextView.text = quantity.toString()
                }
            }

            increaseQuantityButton.setOnClickListener {
                quantity++
                quantityTextView.text = quantity.toString()
            }



            addToCartButton.setOnClickListener { _ ->
                Snackbar.make(
                    addToCartButton,
                    "${it.name} added to cart",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            buyNowButton.setOnClickListener { _ ->
                // Handle buy now action
                Snackbar.make(
                    buyNowButton,
                    "Processing purchase for ${it.name}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}