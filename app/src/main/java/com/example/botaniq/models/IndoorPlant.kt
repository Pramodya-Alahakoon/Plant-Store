package com.example.botaniq.models

data class IndoorPlant(
    val name: String,
    val price: String,
    val imageResource: Int,
    val description: String,
    val tags: List<String>,
    var isFavorite: Boolean
)