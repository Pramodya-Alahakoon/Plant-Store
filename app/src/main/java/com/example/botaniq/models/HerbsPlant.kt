package com.example.botaniq.models

data class HerbsPlant(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val category: String, // "Herb", "Vegetable", "Fruit"
    val growthTime: String,
    val harvestSeason: String,
    val difficulty: String,
    val tags: List<String>,
    val uses: List<String>,
    val createdAt: String = "2025-03-20 11:40:36",
    val createdBy: String = "User",
    var isFavorite: Boolean
)