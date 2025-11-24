package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout

class ProfileActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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

        val profileImageView: ImageView = findViewById(R.id.profileImageView)
        val profileNameTextView: TextView = findViewById(R.id.profileNameTextView)
        val profileEmailTextView: TextView = findViewById(R.id.profileEmailTextView)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Dummy user data, replace with actual user data from your source
        val userName = "User"
        val userEmail = "user@gmail.com"

        profileNameTextView.text = userName
        profileEmailTextView.text = userEmail

        findViewById<TextView>(R.id.personalInfoLink).setOnClickListener {
            val intent = Intent(this, EditPersonalInfoActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.myOrdersLink).setOnClickListener {
            val intent = Intent(this, OrderHistoryActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.promocodesLink).setOnClickListener {
            val intent = Intent(this, PromocodesActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.verifyPhoneNumberLink).setOnClickListener {
            val intent = Intent(this, VerifyPhoneNumberActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.verifyEmailLink).setOnClickListener {
            val intent = Intent(this, VerifyEmailActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.signOutLink).setOnClickListener {
            val intent = Intent(this, ConfirmSignOutActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
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
                    // Stay on the current activity
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
        bottomNavigationView.selectedItemId = R.id.nav_profile
    }
}