package com.example.botaniq.data

import com.example.botaniq.R
import com.example.botaniq.models.BestSellerPlant

object BestSellerPlantsData {
    fun getPlants() = listOf(
        BestSellerPlant(
            name = "Orchids",
            price = "Rs.1400.00",
            imageResource = R.drawable.orchids,
            description = "Elegant flowering plant, perfect for indoor decor",
            rating = 4,
            isPopular = true,
            type = "INDOOR",
            color = "white",
            potType = "CERAMIC",
            salesCount = 150,
            isFavorite = false
        ),
        BestSellerPlant(
            name = "Lavender",
            price = "Rs.950.00",
            imageResource = R.drawable.lavender,
            description = "Fragrant purple flowers, drought-resistant",
            rating = 5,
            isPopular = true,
            type = "OUTDOOR",
            color = "purple",
            potType = "TERRACOTTA",
            salesCount = 120,
            isFavorite = false
        ),
        BestSellerPlant(
            name = "Peace Lily",
            price = "Rs.1550.00",
            imageResource = R.drawable.lily,
            description = "Air-purifying indoor beauty",
            rating = 4,
            isPopular = false,
            type = "INDOOR",
            color = "white",
            potType = "PLASTIC",
            salesCount = 90,
            isFavorite = false
        ),
        BestSellerPlant(
            name = "Cactus",
            price = "Rs.700.00",
            imageResource = R.drawable.cactus,
            description = "Low-maintenance desert plant",
            rating = 5,
            isPopular = true,
            type = "OUTDOOR",
            color = "green",
            potType = "TERRACOTTA",
            salesCount = 100,
            isFavorite = false
        )
    )
}