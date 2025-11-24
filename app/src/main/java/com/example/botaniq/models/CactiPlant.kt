package com.example.botaniq.models

data class CactiPlant(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val type: String, // "Cactus" or "Succulent"
    val lightRequirement: String,
    val lightLevel: Int, // 1-4
    val waterFrequency: String,
    val waterLevel: Int, // 1-4
    val growthRate: String,
    val matureSize: String,
    val tags: List<String>,
    val isFlowering: Boolean,
    val createdAt: String = "2025-03-20 11:56:47",
    val createdBy: String = "User",
    var isFavorite: Boolean
)