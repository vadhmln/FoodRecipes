package ru.vdh.foodrecipes.database.entities

data class ExtendedIngredientsEntity(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)