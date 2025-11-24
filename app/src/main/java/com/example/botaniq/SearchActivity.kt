package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView
import android.widget.TextView

class SearchActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar: Toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        val headerView = navigationView.getHeaderView(0)
        val navHeaderUserName: TextView = headerView.findViewById(R.id.nav_header_user_name)
        val navHeaderUserEmail: TextView = headerView.findViewById(R.id.nav_header_user_email)

        navHeaderUserName.text = "User"
        navHeaderUserEmail.text = "user@gmail.com"

        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val cartIcon: ImageView = findViewById(R.id.cartIcon)
        cartIcon.setOnClickListener {
            startActivity(Intent(this, OrderPageActivity::class.java))
        }

        val searchView: SearchView = findViewById(R.id.searchView)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search query text change
                return false
            }
        })

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
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_search -> {
                    // Stay on the current activity
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

        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_categories -> {
                    // Handle categories click if necessary
                    true
                }
                R.id.nav_indoor -> {
                    startActivity(Intent(this, IndoorPlantsActivity::class.java))
                    true
                }
                R.id.nav_outdoor -> {
                    startActivity(Intent(this, OutdoorPlantsActivity::class.java))
                    true
                }
                R.id.nav_air_purifying -> {
                    startActivity(Intent(this, AirPurifyingPlantsActivity::class.java))
                    true
                }
                R.id.nav_succulents_cacti -> {
                    startActivity(Intent(this, SucculentsCactiActivity::class.java))
                    true
                }
                R.id.nav_flowering -> {
                    startActivity(Intent(this, FloweringPlantsActivity::class.java))
                    true
                }
                R.id.nav_herbs_edibles -> {
                    startActivity(Intent(this, HerbsEdiblesActivity::class.java))
                    true
                }
                R.id.nav_products -> {
                    startActivity(Intent(this, PlantsActivity::class.java))
                    true
                }
                R.id.nav_best_sellers -> {
                    startActivity(Intent(this, BestSellersActivity::class.java))
                    true
                }
                R.id.nav_support -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Set the selected item
        bottomNavigationView.selectedItemId = R.id.nav_search
    }
}