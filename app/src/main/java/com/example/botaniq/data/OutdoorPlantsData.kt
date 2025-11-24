package com.example.botaniq.data

import com.example.botaniq.R
import com.example.botaniq.models.OutdoorPlant

object OutdoorPlantsData {
    fun getPlants() = listOf(
        OutdoorPlant(
            name = "Lavender",
            price = "Rs.600.00",
            imageResource = R.drawable.lavender,
            description = "Fragrant purple flowers, drought-resistant",
            sunRequirement = "Full Sun",
            sunLevel = 4,
            waterRequirement = "Low",
            waterLevel = 1,
            environment = "Full Sun",
            seasonality = "Summer",
            tags = listOf("Sun Loving", "Drought Resistant", "Fragrant"),
            height = "2-3 feet",
            spread = "2-3 feet",
            isFavorite = false
        ),
        OutdoorPlant(
            name = "Hydrangea",
            price = "Rs.650.00",
            imageResource = R.drawable.hydrangea,
            description = "Large, showy blooms in various colors",
            sunRequirement = "Partial Shade",
            sunLevel = 2,
            waterRequirement = "Moderate",
            waterLevel = 3,
            environment = "Partial Shade",
            seasonality = "Summer-Fall",
            tags = listOf("Shade Loving", "Flowering", "Shrub"),
            height = "3-6 feet",
            spread = "3-6 feet",
            isFavorite = false
        ),
        OutdoorPlant(
            name = "Sedum",
            price = "Rs.800.00",
            imageResource = R.drawable.sedum,
            description = "Drought-tolerant succulent ground cover",
            sunRequirement = "Full Sun",
            sunLevel = 4,
            waterRequirement = "Low",
            waterLevel = 1,
            environment = "Full Sun",
            seasonality = "Year-round",
            tags = listOf("Drought Resistant", "Low Maintenance", "Ground Cover"),
            height = "6-8 inches",
            spread = "1-2 feet",
            isFavorite = false
        ),
        OutdoorPlant(
            name = "Japanese Maple",
            price = "Rs.1700.00",
            imageResource = R.drawable.japanesemaple,
            description = "Elegant tree with colorful foliage",
            sunRequirement = "Partial Shade",
            sunLevel = 2,
            waterRequirement = "Moderate",
            waterLevel = 2,
            environment = "Partial Shade",
            seasonality = "Spring-Fall",
            tags = listOf("Shade Loving", "Tree", "Ornamental"),
            height = "10-25 feet",
            spread = "10-25 feet",
            isFavorite = false
        )
    )
}