package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.botaniq.models.AllPlant
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

class WishlistActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var wishlistAdapter: WishlistAdapter
    private var wishlistPlants = mutableListOf(
        AllPlant(
            name = "Orchids",
            price = "Rs.1550",
            imageResource = R.drawable.orchids,
            description = "Elegant flowering plant, perfect for indoor decor",
            rating = 4,
            isPopular = true,
            type = "INDOOR",
            color = "white",
            potType = "CERAMIC",
            isFavorite = true,
            careLevel = "Moderate"
        ),
        AllPlant(
            name = "Lavender",
            price = "Rs.700",
            imageResource = R.drawable.lavender,
            description = "Fragrant purple flowers, drought-resistant",
            rating = 5,
            isPopular = true,
            type = "OUTDOOR",
            color = "purple",
            potType = "TERRACOTTA",
            isFavorite = true,
            careLevel = "Easy"
        ),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        setupViews()
        setupNavigationDrawer()
        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupViews() {
        drawerLayout = findViewById(R.id.drawer_layout)

        val cartIcon: ImageView = findViewById(R.id.cartIcon)
        cartIcon.setOnClickListener {
            startActivity(Intent(this, OrderPageActivity::class.java))
        }

        findViewById<TextView>(R.id.titleText).text = "Wishlist"
        findViewById<TextView>(R.id.subTitleText).text = "Your favorite plants in one place"

    }

    private fun setupNavigationDrawer() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        val headerView = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.nav_header_user_name).text = "User"
        headerView.findViewById<TextView>(R.id.nav_header_user_email).text = "user@gmail.com"

        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.nav_indoor -> {
                    startActivity(Intent(this, IndoorPlantsActivity::class.java))
                    true
                }
                R.id.nav_outdoor -> {
                    startActivity(Intent(this, OutdoorPlantsActivity::class.java))
                    true
                }
                // ... other navigation items ...
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        wishlistAdapter = WishlistAdapter(
            onItemClick = { plant -> handlePlantClick(plant) },
            onRemoveFromWishlist = { plant -> handleRemoveFromWishlist(plant) },
            onAddToCart = { plant -> handleAddToCart(plant) }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@WishlistActivity, 2)
            adapter = wishlistAdapter
            setHasFixedSize(true)
        }

        wishlistAdapter.submitList(wishlistPlants)
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()  // Add this to close current activity
                    true
                }
                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    finish()  // Add this to close current activity
                    true
                }
                R.id.nav_orders -> {
                    startActivity(Intent(this, OrderPageActivity::class.java))
                    finish()  // Add this to close current activity
                    true
                }
                R.id.nav_wishlist -> {
                    // We're already on wishlist, no need to do anything
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()  // Add this to close current activity
                    true
                }
                else -> false
            }
        }

        // Set the selected item to wishlist
        bottomNavigationView.menu.findItem(R.id.nav_wishlist)?.isChecked = true
    }
    private fun handlePlantClick(plant: AllPlant) {
        val intent = Intent(this, PlantDetailsActivity::class.java)
        intent.putExtra("plant", plant)
        startActivity(intent)
    }

    private fun handleRemoveFromWishlist(plant: AllPlant) {
        wishlistPlants.remove(plant)
        wishlistAdapter.submitList(wishlistPlants.toList())

        Snackbar.make(
            findViewById(R.id.coordinator_layout),
            "${plant.name} removed from wishlist",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun handleAddToCart(plant: AllPlant) {
        Snackbar.make(
            findViewById(R.id.coordinator_layout),
            "${plant.name} added to cart",
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

    class WishlistAdapter(
        private val onItemClick: (AllPlant) -> Unit,
        private val onRemoveFromWishlist: (AllPlant) -> Unit,
        private val onAddToCart: (AllPlant) -> Unit
    ) : ListAdapter<AllPlant, WishlistAdapter.WishlistViewHolder>(WishlistDiffCallback()) {

        inner class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val imageView: ImageView = itemView.findViewById(R.id.plantImageView)
            private val nameText: TextView = itemView.findViewById(R.id.plantNameTextView)
            private val descriptionText: TextView = itemView.findViewById(R.id.plantDescriptionTextView)
            private val priceText: TextView = itemView.findViewById(R.id.priceTextView)
            private val typeChip: Chip = itemView.findViewById(R.id.plantTypeChip)
            private val removeButton: ImageView = itemView.findViewById(R.id.removeFromWishlistButton)
            private val addToCartButton: MaterialButton = itemView.findViewById(R.id.addToCartButton)

            fun bind(plant: AllPlant) {
                imageView.setImageResource(plant.imageResource)
                nameText.text = plant.name
                descriptionText.text = plant.description
                priceText.text = plant.price
                typeChip.text = plant.type

                itemView.setOnClickListener { onItemClick(plant) }
                removeButton.setOnClickListener { onRemoveFromWishlist(plant) }
                addToCartButton.setOnClickListener { onAddToCart(plant) }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wishlist_plant, parent, false)
            return WishlistViewHolder(view)
        }

        override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
    }

    private class WishlistDiffCallback : DiffUtil.ItemCallback<AllPlant>() {
        override fun areItemsTheSame(oldItem: AllPlant, newItem: AllPlant): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: AllPlant, newItem: AllPlant): Boolean {
            return oldItem == newItem
        }
    }
}