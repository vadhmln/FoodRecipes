package ru.vdh.foodrecipes.recipes.domain.model

data class ExtendedIngredientDomainModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)