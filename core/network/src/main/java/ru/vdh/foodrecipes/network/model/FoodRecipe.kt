package ru.vdh.foodrecipes.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodRecipe(
    val results: List<Result>
)