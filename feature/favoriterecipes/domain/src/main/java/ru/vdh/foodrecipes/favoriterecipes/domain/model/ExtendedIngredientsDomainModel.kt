package ru.vdh.foodrecipes.favoriterecipes.domain.model

data class ExtendedIngredientsDomainModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)