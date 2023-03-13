package ru.vdh.foodrecipes.network

import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.vdh.foodrecipes.network.model.FoodJokeRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): ApiResponse<RecipesRemoteDataSourceModel>

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery: Map<String, String>
    ): Response<RecipesRemoteDataSourceModel>

    @GET("food/jokes/random")
    suspend fun getFoodJoke(
        @Query("apiKey") apiKey: String
    ): Response<FoodJokeRemoteDataSourceModel>

}