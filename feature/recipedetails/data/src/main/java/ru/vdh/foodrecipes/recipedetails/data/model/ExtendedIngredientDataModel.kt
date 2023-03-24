package ru.vdh.foodrecipes.recipedetails.data.model

data class ExtendedIngredientDataModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)