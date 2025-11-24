package com.example.botaniq.models

data class BestSellerPlant(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val rating: Int,
    val isPopular: Boolean,
    val type: String, // "INDOOR" or "OUTDOOR"
    val color: String,
    val potType: String,
    val salesCount: Int,
    val createdAt: String = "2025-03-20 12:05:16",
    val createdBy: String = "User",
    var isFavorite: Boolean
)