package ru.vdh.foodrecipes.favoriterecipes.presentation.model

data class ExtendedIngredientsPresentationModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)