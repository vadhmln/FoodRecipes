package ru.vdh.foodrecipes.recipes.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipes.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository

class RecipesRepositoryImpl(
    private val recipesRemoteDataSource: RecipesRemoteDataSource,
    private val recipesDataToDomainMapper: RecipesDataToDomainMapper
) : RecipesRepository {

    override suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = recipesRemoteDataSource.getRecipes(queries, onStart, onComplete, onError)
        .map(recipesDataToDomainMapper::toDomain)


    override suspend fun searchRecipes(searchQuery: Map<String, String>): List<RecipesDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getFoodJoke(apiKey: String): List<FoodJokeDomainModel> {
        TODO("Not yet implemented")
    }
}


