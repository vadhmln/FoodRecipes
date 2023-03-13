package ru.vdh.foodrecipes.network.model

import com.google.gson.annotations.SerializedName

data class ResultRemoteDataSourceModel(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int,
    @SerializedName("cheap")
    val cheap: Boolean,
    @SerializedName("dairyFree")
    val dairyFree: Boolean,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredientRemoteDataSourceModel>,
    @SerializedName("glutenFree")
    val glutenFree: Boolean,
    @SerializedName("id")
    val recipeId: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int,
    @SerializedName("sourceName")
    val sourceName: String?,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vegan")
    val vegan: Boolean,
    @SerializedName("vegetarian")
    val vegetarian: Boolean,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean,

//    val aggregateLikes: Int,
//    val cheap: Boolean,
//    val dairyFree: Boolean,
//    val extendedIngredientRemoteDataSourceModels: List<ExtendedIngredientRemoteDataSourceModel>,
//    val glutenFree: Boolean,
//    val recipeId: Int,
//    val image: String,
//    val readyInMinutes: Int,
//    val sourceName: String?,
//    val sourceUrl: String,
//    val summary: String,
//    val title: String,
//    val vegan: Boolean,
//    val vegetarian: Boolean,
//    val veryHealthy: Boolean,
)