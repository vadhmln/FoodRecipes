package ru.vdh.foodrecipes.favoriterecipes.data.model

data class ExtendedIngredientsDataModel(
    val amount: Double,
    val consistency: String,
    val image: String?,
    val name: String,
    val original: String,
    val unit: String
)