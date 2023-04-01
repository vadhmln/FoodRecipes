package ru.vdh.foodrecipes.recipes.datasource

import android.util.Log
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.network.FoodRecipesApi
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.datasource.mapper.ErrorResponseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataModelToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.ResultDataToDatabaseMapper

class RecipesRemoteDataSourceImpl(
    private val foodRecipesApi: FoodRecipesApi,
    private val recipesDao: RecipesDao,
    private val recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
    private val resultDataToDatabaseMapper: ResultDataToDatabaseMapper,
    private val recipesDataModelToDatabaseMapper: RecipesDataModelToDatabaseMapper,
) : RecipesRemoteDataSource {

    override suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        val response = foodRecipesApi.getRecipes(queries)

        Log.d("RecipesRemoteDataSourceImpl", "foodRecipesApi called!")

        response.suspendOnSuccess {
            val recipe = recipesRemoteDataSourceToDataMapper.toData(data)
            val recipeEntity =
                RecipesEntity(recipesDataModelToDatabaseMapper.toDatabase(recipe))

            recipesDao.insertRecipes(recipeEntity)

            recipeEntity.foodRecipe.results.forEach {
                recipesDao.insertResultRecipes(resultDataToDatabaseMapper.toDatabase(it))
            }
            emit(recipe)

        }.suspendOnError {
            Log.d("onError", message())
            map(ErrorResponseMapper) {
                onError("[Code: $code]: $message")
                Log.d("ErrorResponseMapper", "[Code: $code]: $message")
                when {
                    code == 402 -> {
                        message = "API Key Limited."
                    }

                    message().contains("timeout") -> {
                        message = "Timeout."
                    }
                }
            }
        }.onFailure {
            onError(message())
            Log.d("onError", message())
        }.suspendOnException { error(message()) }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchRecipes(searchQuery: Map<String, String>) = flow {

        val response = foodRecipesApi.searchRecipes(searchQuery)

        response.suspendOnSuccess {
            val recipe = recipesRemoteDataSourceToDataMapper.toData(data)
            val recipeEntity =
                RecipesEntity(recipesDataModelToDatabaseMapper.toDatabase(recipe))
            recipesDao.insertRecipes(recipeEntity)
            emit(recipe)
        }.onFailure {
            Log.d("onError", message())
        }.suspendOnException { error(message()) }

    }.flowOn(Dispatchers.IO)
}


