package com.example.botaniq

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.adapters.BestSellerPlantAdapter
import com.example.botaniq.data.BestSellerPlantsData
import com.example.botaniq.models.BestSellerPlant
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar

class BestSellersActivity : AppCompatActivity() {

    private lateinit var plantAdapter: BestSellerPlantAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var filterChipGroup: ChipGroup

    private val plants = BestSellerPlantsData.getPlants()
    private var filteredPlants = mutableListOf<BestSellerPlant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_sellers)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        initializeViews()
        setupRecyclerView()
        setupSearch()
        setupFilters()

        filteredPlants.addAll(plants)
        plantAdapter.submitList(filteredPlants.toList())
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        filterChipGroup = findViewById(R.id.filterChipGroup)
    }

    private fun setupRecyclerView() {
        plantAdapter = BestSellerPlantAdapter(
            onItemClick = { plant -> handlePlantClick(plant) },
            onFavoriteClick = { plant -> handleFavoriteClick(plant) },
            onAddToCartClick = { plant -> handleAddToCartClick(plant) }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@BestSellersActivity, 2)
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
        filterChipGroup.setOnCheckedChangeListener { _, _ ->
            filterPlants()
        }
    }

    private fun filterPlants() {
        val searchQuery = searchEditText.text.toString().lowercase()
        val selectedFilters = filterChipGroup.checkedChipIds.map {
            findViewById<Chip>(it).text.toString()
        }

        filteredPlants.clear()
        filteredPlants.addAll(plants.filter { plant ->
            val matchesSearch = plant.name.lowercase().contains(searchQuery) ||
                    plant.description.lowercase().contains(searchQuery)

            val matchesFilter = if (selectedFilters.isEmpty()) {
                true
            } else {
                selectedFilters.any { filter ->
                    when (filter) {
                        "Most Popular" -> plant.isPopular
                        "Top Rated" -> plant.rating >= 4
                        else -> plant.type.uppercase() == filter.uppercase()
                    }
                }
            }

            matchesSearch && matchesFilter
        })

        plantAdapter.submitList(filteredPlants.toList())
    }

    private fun handlePlantClick(plant: BestSellerPlant) {
        // Navigate to plant detail activity
    }

    private fun handleFavoriteClick(plant: BestSellerPlant) {
        val index = filteredPlants.indexOf(plant)
        if (index != -1) {
            val updatedPlant = plant.copy(isFavorite = !plant.isFavorite)
            filteredPlants[index] = updatedPlant
            plantAdapter.submitList(filteredPlants.toList())

            val message = if (updatedPlant.isFavorite)
                "${plant.name} added to wishlist"
            else
                "${plant.name} removed from wishlist"

            Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleAddToCartClick(plant: BestSellerPlant) {
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