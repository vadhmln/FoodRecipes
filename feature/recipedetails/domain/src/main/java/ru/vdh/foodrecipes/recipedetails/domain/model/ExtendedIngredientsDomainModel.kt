package ru.vdh.foodrecipes.recipedetails.domain.model

data class ExtendedIngredientsDomainModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)