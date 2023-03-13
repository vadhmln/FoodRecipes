package ru.vdh.foodrecipes.recipes.data.datasource

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.vdh.foodrecipes.recipes.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel

interface RecipesRemoteDataSource {

    suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<RecipesDataModel>

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<RecipesDataModel>

    suspend fun getFoodJoke(apiKey: String): Response<FoodJokeDataModel>
}