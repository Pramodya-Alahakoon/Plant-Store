package com.example.botaniq

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.adapters.IndoorPlantAdapter
import com.example.botaniq.models.IndoorPlant
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class IndoorPlantsActivity : AppCompatActivity() {

    private lateinit var plantAdapter: IndoorPlantAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var filterChipGroup: ChipGroup

    private val plants = listOf(
        IndoorPlant(
            name = "Peace Lily",
            price = "Rs.700.00",
            imageResource = R.drawable.lily,
            description = "Perfect air-purifying plant for low-light conditions",
            tags = listOf("Low Light", "Air Purifying"),
            isFavorite = false
        ),
        IndoorPlant(
            name = "Snake Plant",
            price = "Rs.1100.00",
            imageResource = R.drawable.airpurifyng,
            description = "Extremely low maintenance, perfect for beginners",
            tags = listOf("Low Light", "Air Purifying", "Easy Care"),
            isFavorite = false
        ),
        // Add more plants...
    )

    private var filteredPlants = mutableListOf<IndoorPlant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor_plants)

        initializeViews()
        setupRecyclerView()
        setupSearch()
        setupFilters()

        // Initial display
        filteredPlants.addAll(plants)
        plantAdapter.submitList(filteredPlants.toList())
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        filterChipGroup = findViewById(R.id.filterChipGroup)
    }

    private fun setupRecyclerView() {
        plantAdapter = IndoorPlantAdapter(
            onItemClick = { plant -> handlePlantClick(plant) },
            onFavoriteClick = { plant -> handleFavoriteClick(plant) },
            onAddToCartClick = { plant -> handleAddToCartClick(plant) }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@IndoorPlantsActivity, 2)
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
                selectedFilters.any { filter -> plant.tags.contains(filter) }
            }

            matchesSearch && matchesFilter
        })

        plantAdapter.submitList(filteredPlants.toList())
    }

    private fun handlePlantClick(plant: IndoorPlant) {
        // Navigate to plant detail
    }

    private fun handleFavoriteClick(plant: IndoorPlant) {
        val index = filteredPlants.indexOf(plant)
        if (index != -1) {
            filteredPlants[index] = plant.copy(isFavorite = !plant.isFavorite)
            plantAdapter.submitList(filteredPlants.toList())
        }
    }

    private fun handleAddToCartClick(plant: IndoorPlant) {
        // Add to cart logic
    }
}