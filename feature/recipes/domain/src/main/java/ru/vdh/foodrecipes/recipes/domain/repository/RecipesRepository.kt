package ru.vdh.foodrecipes.recipes.domain.repository

import ru.vdh.foodrecipes.recipes.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipes.domain.model.RecipeErrorMassageDomainModel

interface RecipesRepository {

    suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<RecipesDomainModel>

    suspend fun searchRecipes(searchQuery: Map<String, String>): List<RecipesDomainModel>

    suspend fun getFoodJoke(apiKey: String): List<FoodJokeDomainModel>

}