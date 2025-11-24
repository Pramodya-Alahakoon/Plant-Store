package com.example.botaniq

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.adapters.CactiPlantAdapter
import com.example.botaniq.data.CactiPlantsData
import com.example.botaniq.models.CactiPlant
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar

class SucculentsCactiActivity : AppCompatActivity() {

    private lateinit var plantAdapter: CactiPlantAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var filterChipGroup: ChipGroup

    private val plants = CactiPlantsData.getPlants()
    private var filteredPlants = mutableListOf<CactiPlant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succulents_cacti)

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
        plantAdapter = CactiPlantAdapter(
            onItemClick = { plant -> handlePlantClick(plant) },
            onFavoriteClick = { plant -> handleFavoriteClick(plant) },
            onAddToCartClick = { plant -> handleAddToCartClick(plant) }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@SucculentsCactiActivity, 2)
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
                    plant.type == filter ||
                            plant.tags.contains(filter) ||
                            (filter == "Flowering" && plant.isFlowering)
                }
            }

            matchesSearch && matchesFilter
        })

        plantAdapter.submitList(filteredPlants.toList())
    }

    private fun handlePlantClick(plant: CactiPlant) {
        // Navigate to plant detail activity
    }

    private fun handleFavoriteClick(plant: CactiPlant) {
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

    private fun handleAddToCartClick(plant: CactiPlant) {
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