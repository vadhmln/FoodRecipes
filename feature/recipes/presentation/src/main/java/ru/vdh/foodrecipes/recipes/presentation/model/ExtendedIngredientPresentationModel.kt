package ru.vdh.foodrecipes.recipes.presentation.model

data class ExtendedIngredientPresentationModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)