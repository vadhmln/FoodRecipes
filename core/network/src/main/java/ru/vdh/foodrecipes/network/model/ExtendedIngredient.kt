package ru.vdh.foodrecipes.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredient(
    val amount: Double,
    val consistency: String,
    val image: String,
    val name: String,
    val original: String,
    val unit: String
)