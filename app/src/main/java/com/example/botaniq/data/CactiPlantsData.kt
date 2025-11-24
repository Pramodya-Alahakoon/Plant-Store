package com.example.botaniq.data

import com.example.botaniq.R
import com.example.botaniq.models.CactiPlant

object CactiPlantsData {
    fun getPlants() = listOf(
        CactiPlant(
            name = "Golden Barrel Cactus",
            price = "Rs.1100.00",
            imageResource = R.drawable.golden,
            description = "Spherical cactus with golden spines",
            type = "Cactus",
            lightRequirement = "Full Sun",
            lightLevel = 4,
            waterFrequency = "Very Low",
            waterLevel = 1,
            growthRate = "Slow",
            matureSize = "2 feet",
            tags = listOf("Cacti", "Easy Care"),
            isFlowering = false,
            isFavorite = false
        ),
        CactiPlant(
            name = "Aloe Vera",
            price = "Rs.500.00",
            imageResource = R.drawable.aloevera,
            description = "Medicinal succulent with thick leaves",
            type = "Succulent",
            lightRequirement = "Bright Indirect",
            lightLevel = 3,
            waterFrequency = "Low",
            waterLevel = 2,
            growthRate = "Moderate",
            matureSize = "1-2 feet",
            tags = listOf("Succulents", "Easy Care"),
            isFlowering = true,
            isFavorite = false
        ),
        CactiPlant(
            name = "Zebra Plant",
            price = "Rs.800.00",
            imageResource = R.drawable.zebraplant,
            description = "Small succulent with striped patterns",
            type = "Succulent",
            lightRequirement = "Bright Indirect",
            lightLevel = 3,
            waterFrequency = "Low",
            waterLevel = 2,
            growthRate = "Slow",
            matureSize = "6 inches",
            tags = listOf("Succulents", "Easy Care"),
            isFlowering = false,
            isFavorite = false
        ),
        CactiPlant(
            name = "Easter Cactus",
            price = "Rs.1450.00",
            imageResource = R.drawable.cactus,
            description = "Flowering cactus with colorful blooms",
            type = "Cactus",
            lightRequirement = "Partial Sun",
            lightLevel = 2,
            waterFrequency = "Moderate",
            waterLevel = 2,
            growthRate = "Moderate",
            matureSize = "1 foot",
            tags = listOf("Cacti", "Flowering"),
            isFlowering = true,
            isFavorite = false
        )
    )
}