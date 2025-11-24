package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shopNowButton: Button = findViewById(R.id.shopNowButton)
        val shopNowButton2: Button = findViewById(R.id.shopNowButton2)
        val viewAllTextView: TextView = findViewById(R.id.viewAllTextView)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        shopNowButton.setOnClickListener {
            // Redirect to PlantsActivity
            val intent = Intent(this, PlantsActivity::class.java)
            startActivity(intent)

        }

        shopNowButton2.setOnClickListener {
            // Redirect to Best Sellers page
            val intent = Intent(this, BestSellersActivity::class.java)
            startActivity(intent)

        }

        viewAllTextView.setOnClickListener {
            // Redirect to Best Sellers page
            val intent = Intent(this, BestSellersActivity::class.java)
            startActivity(intent)
        }

        findViewById<MaterialCardView>(R.id.indoorCard).setOnClickListener {
            startActivity(Intent(this, IndoorPlantsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.outdoorCard).setOnClickListener {
            startActivity(Intent(this, OutdoorPlantsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.airPurifyingCard).setOnClickListener {
            startActivity(Intent(this, AirPurifyingPlantsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.succulentsCactiCard).setOnClickListener {
            startActivity(Intent(this, SucculentsCactiActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.floweringPlantsCard).setOnClickListener {
            startActivity(Intent(this, FloweringPlantsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.herbsEdiblesCard).setOnClickListener {
            startActivity(Intent(this, HerbsEdiblesActivity::class.java))
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Stay on the current activity
                    true
                }
                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.nav_orders -> {
                    startActivity(Intent(this, OrderPageActivity::class.java))
                    true
                }
                R.id.nav_wishlist -> {
                    startActivity(Intent(this, WishlistActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Set the selected item
        bottomNavigationView.selectedItemId = R.id.nav_home
    }
}