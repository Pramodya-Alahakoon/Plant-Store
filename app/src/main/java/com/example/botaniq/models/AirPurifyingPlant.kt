package com.example.botaniq.models

data class AirPurifyingPlant(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val purificationLevel: Int, // 1-5
    val maintenanceLevel: String,
    val toxinsRemoved: List<String>,
    val tags: List<String>,
    var isFavorite: Boolean
) {
    fun getPurificationText(): String {
        return when (purificationLevel) {
            5 -> "Excellent"
            4 -> "Very Good"
            3 -> "Good"
            2 -> "Moderate"
            else -> "Basic"
        }
    }

    fun getToxinsRemovedText(): String {
        return toxinsRemoved.joinToString(", ")
    }
}