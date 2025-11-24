package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class OrderPageActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var plantImageView: ImageView
    private lateinit var plantNameTextView: TextView
    private lateinit var plantPriceTextView: TextView
    private lateinit var decrementButton: MaterialButton
    private lateinit var incrementButton: MaterialButton
    private lateinit var quantityTextView: TextView
    private lateinit var subtotalTextView: TextView
    private lateinit var deliveryCostTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var continueButton: MaterialButton
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var menuIcon: ImageView
    private lateinit var cartIcon: ImageView

    private var quantity = 1
    private val plantPrice = 50.00
    private val deliveryCost = 200.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_page)

        initializeViews()
        setupToolbar()
        setupNavigationDrawer()
        setupCartItems()
        setupQuantityControls()
        setupBottomNavigation()
        updateTotals()
    }

    private fun initializeViews() {
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.tool_bar)
        navigationView = findViewById(R.id.nav_view)
        menuIcon = findViewById(R.id.menuIcon)
        cartIcon = findViewById(R.id.cartIcon)

        plantImageView = findViewById(R.id.plantImageView)
        plantNameTextView = findViewById(R.id.plantNameTextView)
        plantPriceTextView = findViewById(R.id.plantPriceTextView)
        decrementButton = findViewById(R.id.decrementButton)
        incrementButton = findViewById(R.id.incrementButton)
        quantityTextView = findViewById(R.id.quantityTextView)
        subtotalTextView = findViewById(R.id.subtotalTextView)
        deliveryCostTextView = findViewById(R.id.deliveryCostTextView)
        totalTextView = findViewById(R.id.totalTextView)
        continueButton = findViewById(R.id.continueButton)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun setupNavigationDrawer() {
        val headerView = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.nav_header_user_name).text = "User"
        headerView.findViewById<TextView>(R.id.nav_header_user_email).text = "user@gmail.com"

        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_categories -> true
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
    }

    private fun setupCartItems() {
        plantImageView.setImageResource(R.drawable.orchids)
        plantNameTextView.text = "Orchids"
        updateQuantityAndPrice()
        deliveryCostTextView.text = String.format("Rs: %.2f", deliveryCost)
    }

    private fun setupQuantityControls() {
        decrementButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantityAndPrice()
                showSnackbar("Quantity updated")
            }
        }

        incrementButton.setOnClickListener {
            quantity++
            updateQuantityAndPrice()
            showSnackbar("Quantity updated")
        }
    }

    private fun setupClickListeners() {
        continueButton.setOnClickListener {
            startActivity(Intent(this, ShippingPaymentInfoActivity::class.java))
        }

        cartIcon.setOnClickListener {
            showSnackbar("Already in cart")
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_orders -> true
                R.id.nav_wishlist -> {
                    startActivity(Intent(this, WishlistActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = R.id.nav_orders
    }

    private fun updateQuantityAndPrice() {
        quantityTextView.text = quantity.toString()
        plantPriceTextView.text = String.format("Rs: %.2f", plantPrice * quantity)
        updateTotals()
    }

    private fun updateTotals() {
        val subtotal = plantPrice * quantity
        val total = subtotal + deliveryCost

        subtotalTextView.text = String.format("Rs: %.2f", subtotal)
        totalTextView.text = String.format("Rs: %.2f", total)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}