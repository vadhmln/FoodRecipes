package ru.vdh.foodrecipes.network.model

import com.google.gson.annotations.SerializedName

data class RecipesRemoteDataSourceModel(
    @SerializedName("results")
    val results: List<ResultRemoteDataSourceModel>
)