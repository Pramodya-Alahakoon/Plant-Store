package com.example.botaniq.data

import com.example.botaniq.R
import com.example.botaniq.models.HerbsPlant

object HerbsPlantsData {
    fun getPlants() = listOf(
        HerbsPlant(
            name = "Basil",
            price = "Rs.850.00",
            imageResource = R.drawable.basil,
            description = "Sweet aromatic herb, perfect for Italian cuisine",
            category = "Herb",
            growthTime = "3-4 weeks",
            harvestSeason = "Year-round",
            difficulty = "Easy",
            tags = listOf("Herbs", "Easy Growing", "Kitchen Garden"),
            uses = listOf("Cooking", "Garnishing", "Tea"),
            isFavorite = false
        ),
        HerbsPlant(
            name = "Cherry Tomatoes",
            price = "Rs.450.00",
            imageResource = R.drawable.tomatoes,
            description = "Sweet and juicy miniature tomatoes",
            category = "Vegetable",
            growthTime = "8-10 weeks",
            harvestSeason = "Summer",
            difficulty = "Moderate",
            tags = listOf("Vegetables", "Easy Growing"),
            uses = listOf("Salads", "Cooking", "Snacking"),
            isFavorite = false
        ),
        HerbsPlant(
            name = "Mint",
            price = "Rs.700.00",
            imageResource = R.drawable.mint,
            description = "Refreshing herb with multiple culinary uses",
            category = "Herb",
            growthTime = "2-3 weeks",
            harvestSeason = "Year-round",
            difficulty = "Easy",
            tags = listOf("Herbs", "Easy Growing"),
            uses = listOf("Tea", "Cocktails", "Garnishing"),
            isFavorite = false
        ),
        HerbsPlant(
            name = "Strawberry",
            price = "Rs.650.00",
            imageResource = R.drawable.strawberry,
            description = "Sweet home-grown berries",
            category = "Fruit",
            growthTime = "4-6 months",
            harvestSeason = "Spring-Summer",
            difficulty = "Moderate",
            tags = listOf("Fruits", "Container Friendly"),
            uses = listOf("Fresh Eating", "Desserts", "Jam"),
            isFavorite = false
        ),
        HerbsPlant(
            name = "Rosemary",
            price = "Rs.1100.00",
            imageResource = R.drawable.rosemary,
            description = "Aromatic Mediterranean herb",
            category = "Herb",
            growthTime = "2-3 months",
            harvestSeason = "Year-round",
            difficulty = "Easy",
            tags = listOf("Herbs", "Easy Growing"),
            uses = listOf("Cooking", "Tea", "Aromatherapy"),
            isFavorite = false
        )
    )
}