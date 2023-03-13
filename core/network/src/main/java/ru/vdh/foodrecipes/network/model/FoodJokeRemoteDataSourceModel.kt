package ru.vdh.foodrecipes.network.model

import com.google.gson.annotations.SerializedName

data class FoodJokeRemoteDataSourceModel(
    @SerializedName("text")
    val text: String
)