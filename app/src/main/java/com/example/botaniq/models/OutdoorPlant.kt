package com.example.botaniq.models

data class OutdoorPlant(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val sunRequirement: String,
    val sunLevel: Int, // 1-4
    val waterRequirement: String,
    val waterLevel: Int, // 1-4
    val environment: String, // "Full Sun", "Partial Shade", "Full Shade"
    val seasonality: String,
    val tags: List<String>,
    val height: String,
    val spread: String,
    val createdAt: String = "2025-03-20 11:48:20",
    val createdBy: String = "User",
    var isFavorite: Boolean
)