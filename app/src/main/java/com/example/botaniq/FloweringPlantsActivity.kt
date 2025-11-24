package com.example.botaniq

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.botaniq.adapters.FloweringPlantAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar

class FloweringPlantsActivity : AppCompatActivity() {

    private lateinit var plantAdapter: FloweringPlantAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var filterChipGroup: ChipGroup

    private val plants = listOf(
        FloweringPlant(
            name = "Rose",
            price = "Rs.900.00",
            imageResource = R.drawable.rose,
            description = "Classic beauty with enchanting fragrance",
            season = "Spring-Summer",
            sunlight = "Full Sun",
            watering = "Moderate",
            tags = listOf("Perennial", "Fragrant", "Cut Flowers"),
            isFavorite = false
        ),
        FloweringPlant(
            name = "Orchids",
            price = "Rs.1350.00",
            imageResource = R.drawable.orchids,
            description = "Elegant and long-lasting blooms",
            season = "Year-round",
            sunlight = "Bright Indirect",
            watering = "Light",
            tags = listOf("Indoor", "Perennial"),
            isFavorite = false
        ),
        FloweringPlant(
            name = "Tulip",
            price = "Rs.700.00",
            imageResource = R.drawable.lily,
            description = "Spring favorite with vibrant colors",
            season = "Spring",
            sunlight = "Full Sun",
            watering = "Moderate",
            tags = listOf("Annual", "Cut Flowers"),
            isFavorite = false
        ),
        FloweringPlant(
            name = "Lavender",
            price = "Rs.650.00",
            imageResource = R.drawable.lavender,
            description = "Fragrant purple blooms with calming aroma",
            season = "Summer",
            sunlight = "Full Sun",
            watering = "Low",
            tags = listOf("Perennial", "Fragrant"),
            isFavorite = false
        ),
        FloweringPlant(
            name = "Jasmine",
            price = "Rs.800.00",
            imageResource = R.drawable.mint,
            description = "Sweet-scented white flowers",
            season = "Spring-Summer",
            sunlight = "Partial Sun",
            watering = "Moderate",
            tags = listOf("Perennial", "Fragrant", "Climber"),
            isFavorite = false
        )
    )

    private var filteredPlants = mutableListOf<FloweringPlant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowering_plants)

        // Setup toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""  // Empty title since we have a custom title

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
        plantAdapter = FloweringPlantAdapter(
            onItemClick = { plant -> handlePlantClick(plant) },
            onFavoriteClick = { plant -> handleFavoriteClick(plant) },
            onAddToCartClick = { plant -> handleAddToCartClick(plant) }
        )

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@FloweringPlantsActivity, 2)
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

    private fun handlePlantClick(plant: FloweringPlant) {
        // Navigate to plant detail activity
        // Intent to PlantDetailActivity with plant data
    }

    private fun handleFavoriteClick(plant: FloweringPlant) {
        val index = filteredPlants.indexOf(plant)
        if (index != -1) {
            val updatedPlant = plant.copy(isFavorite = !plant.isFavorite)
            filteredPlants[index] = updatedPlant
            plantAdapter.submitList(filteredPlants.toList())

            // Show feedback to user
            val message = if (updatedPlant.isFavorite)
                "${plant.name} added to wishlist"
            else
                "${plant.name} removed from wishlist"

            Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun handleAddToCartClick(plant: FloweringPlant) {
        // Add to cart logic
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

data class FloweringPlant(
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val season: String,
    val sunlight: String,
    val watering: String,
    val tags: List<String>,
    var isFavorite: Boolean
)