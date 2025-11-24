package com.example.botaniq

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.adapters.AllPlantAdapter
import com.example.botaniq.models.AllPlant
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class PlantsActivity : AppCompatActivity() {
    private lateinit var plantAdapter: AllPlantAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var filterChipGroup: ChipGroup
    private lateinit var cartIcon: ImageView

    private val currentUser = "SkillCeylon"
    private val currentDateTime = "2025-03-20 17:07:15"

    private val plants = listOf(
        AllPlant(
            name = "Orchids",
            price = "Rs.550.00",
            imageResource = R.drawable.orchids,
            description = "Elegant flowering plant, perfect for indoor decor",
            rating = 4,
            isPopular = true,
            type = "INDOOR",
            color = "white",
            potType = "CERAMIC",
            isFavorite = false,
            careLevel = "Moderate"
        ),
        AllPlant(
            name = "Lavender",
            price = "Rs.800.00",
            imageResource = R.drawable.lavender,
            description = "Fragrant purple flowers, drought-resistant",
            rating = 5,
            isPopular = true,
            type = "OUTDOOR",
            color = "purple",
            potType = "TERRACOTTA",
            isFavorite = false,
            careLevel = "Easy"
        ),
        AllPlant(
            name = "Peace Lily",
            price = "Rs.650.00",
            imageResource = R.drawable.lily,
            description = "Air-purifying indoor beauty",
            rating = 3,
            isPopular = false,
            type = "INDOOR",
            color = "white",
            potType = "PLASTIC",
            isFavorite = false,
            careLevel = "Easy"
        ),
        AllPlant(
            name = "Haworthia",
            price = "Rs.950.00",
            imageResource = R.drawable.haworthia,
            description = "Small succulent perfect for desks",
            rating = 4,
            isPopular = false,
            type = "INDOOR",
            color = "green",
            potType = "CERAMIC",
            isFavorite = false,
            careLevel = "Easy"
        ),
        AllPlant(
            name = "Cactus",
            price = "Rs.450.00",
            imageResource = R.drawable.cactus,
            description = "Low-maintenance desert plant",
            rating = 5,
            isPopular = true,
            type = "OUTDOOR",
            color = "green",
            potType = "TERRACOTTA",
            isFavorite = false,
            careLevel = "Easy"
        ),
        AllPlant(
            name = "Mint",
            price = "Rs.700.00",
            imageResource = R.drawable.mint,
            description = "Fresh herb for cooking and tea",
            rating = 4,
            isPopular = false,
            type = "OUTDOOR",
            color = "green",
            potType = "PLASTIC",
            isFavorite = false,
            careLevel = "Moderate"
        ),
        AllPlant(
            name = "Rose",
            price = "Rs.900.00",
            imageResource = R.drawable.rose,
            description = "Classic flowering beauty",
            rating = 4,
            isPopular = true,
            type = "OUTDOOR",
            color = "red",
            potType = "CERAMIC",
            isFavorite = false,
            careLevel = "Difficult"
        ),
        AllPlant(
            name = "Basil",
            price = "Rs.850.00",
            imageResource = R.drawable.basil,
            description = "Essential culinary herb",
            rating = 5,
            isPopular = false,
            type = "INDOOR",
            color = "green",
            potType = "TERRACOTTA",
            isFavorite = false,
            careLevel = "Easy"
        )
    )

    private var filteredPlants = mutableListOf<AllPlant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plants)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        initializeViews()
        setupRecyclerView()
        setupSearch()
        setupFilters()
        setupCartIcon()

        filteredPlants.addAll(plants)
        plantAdapter.submitList(filteredPlants.toList())
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        filterChipGroup = findViewById(R.id.filterChipGroup)
        cartIcon = findViewById(R.id.cartIcon)
    }

    private fun setupRecyclerView() {
        plantAdapter = AllPlantAdapter(
            onItemClick = { plant -> handlePlantClick(plant) },
            onFavoriteClick = { plant -> handleFavoriteClick(plant) },
            onAddToCartClick = { plant -> handleAddToCartClick(plant) }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@PlantsActivity, 2)
            adapter = plantAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupSearch() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterPlants()
            }
        })
    }

    private fun setupFilters() {
        val filters = listOf("Indoor", "Outdoor", "Popular", "Top Rated", "Easy Care")

        filters.forEach { filterName ->
            val chip = Chip(this).apply {
                text = filterName
                isCheckable = true
                setChipBackgroundColorResource(R.color.chip_background_color)
                setTextColor(resources.getColorStateList(R.color.chip_text_color, null))
            }
            filterChipGroup.addView(chip)
        }

        filterChipGroup.setOnCheckedStateChangeListener { _, _ ->
            filterPlants()
        }
    }

    private fun setupCartIcon() {
        cartIcon.setOnClickListener {
            startActivity(Intent(this, OrderPageActivity::class.java))
        }
    }

    private fun filterPlants() {
        val searchQuery = searchEditText.text.toString().lowercase()
        val selectedChips = filterChipGroup.checkedChipIds.map {
            findViewById<Chip>(it).text.toString()
        }

        val filteredList = plants.filter { plant ->
            val matchesSearch = if (searchQuery.isBlank()) {
                true
            } else {
                plant.name.lowercase().contains(searchQuery) ||
                        plant.description.lowercase().contains(searchQuery)
            }

            val matchesFilters = if (selectedChips.isEmpty()) {
                true
            } else {
                selectedChips.any { filter ->
                    when (filter) {
                        "Indoor" -> plant.type == "INDOOR"
                        "Outdoor" -> plant.type == "OUTDOOR"
                        "Popular" -> plant.isPopular
                        "Top Rated" -> plant.rating >= 4
                        "Easy Care" -> plant.careLevel == "Easy"
                        else -> false
                    }
                }
            }

            matchesSearch && matchesFilters
        }

        filteredPlants.clear()
        filteredPlants.addAll(filteredList)
        plantAdapter.submitList(filteredList)

        if (filteredList.isEmpty()) {
            showEmptyState()
        } else {
            hideEmptyState()
        }
    }

    private fun showEmptyState() {
        findViewById<View>(R.id.emptyStateLayout)?.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideEmptyState() {
        findViewById<View>(R.id.emptyStateLayout)?.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun handlePlantClick(plant: AllPlant) {
        val intent = Intent(this, PlantDetailsActivity::class.java)
        intent.putExtra("plant", plant)
        startActivity(intent)
    }

    private fun handleFavoriteClick(plant: AllPlant) {
        val index = filteredPlants.indexOf(plant)
        if (index != -1) {
            val updatedPlant = plant.copy(isFavorite = !plant.isFavorite)
            filteredPlants[index] = updatedPlant

            // Update the original list as well
            val originalIndex = plants.indexOf(plant)
            if (originalIndex != -1) {
                plants.toMutableList()[originalIndex] = updatedPlant
            }

            plantAdapter.submitList(filteredPlants.toList())

            val message = if (updatedPlant.isFavorite)
                "${plant.name} added to wishlist"
            else
                "${plant.name} removed from wishlist"

            Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleAddToCartClick(plant: AllPlant) {
        Snackbar.make(
            recyclerView,
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
}