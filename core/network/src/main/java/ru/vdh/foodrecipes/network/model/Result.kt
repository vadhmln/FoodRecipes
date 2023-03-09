package ru.vdh.foodrecipes.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients:List<ExtendedIngredient>,
    val glutenFree: Boolean,
    val recipeId: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)