package ru.vdh.foodrecipes.recipes.datasource

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import retrofit2.Response
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.network.FoodRecipesApi
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.datasource.mapper.ErrorResponseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.JokesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataModelToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper

class RecipesRemoteDataSourceImpl(
    private val foodRecipesApi: FoodRecipesApi,
    private val recipesDao: RecipesDao,
    private val recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
    private val recipesDataToDatabaseMapper: RecipesDataToDatabaseMapper,
    private val recipesDatabaseToDataMapper: RecipesDatabaseToDataMapper,
    private val recipesDataModelToDatabaseMapper: RecipesDataModelToDatabaseMapper,
) : RecipesRemoteDataSource {

//    @WorkerThread
    override suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

//        var recipes = recipesDatabaseToDataMapper.toData(recipesDao.readRecipes())

        val response = foodRecipesApi.getRecipes(queries)

        response.suspendOnSuccess {
            val recipe = recipesRemoteDataSourceToDataMapper.toData(data)
            emit(recipe)
        }.suspendOnError {
            map(ErrorResponseMapper) {
                onError("[Code: $code]: $message")

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
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchRecipes(searchQuery: Map<String, String>): Response<RecipesDataModel> {
        TODO()
//        foodRecipesApi.searchRecipes(searchQuery).map(recipesRemoteDataSourceToDataMapper::toData)
    }

    override suspend fun getFoodJoke(apiKey: String): Response<FoodJokeDataModel> {
        TODO()
//        return foodRecipesApi.getFoodJoke(apiKey).map(jokesRemoteDataSourceToDataMapper::toData)
    }

//    private fun handleFoodRecipesResponse(response: Response<RecipesPresentationModel>): NetworkResult<RecipesPresentationModel> {
//        when {
//            response.message().toString().contains("timeout") -> {
//                return NetworkResult.Error("Timeout")
//            }
//
//            response.code() == 402 -> {
//                return NetworkResult.Error("API Key Limited.")
//            }
//
//            response.body()!!.results.isNullOrEmpty() -> {
//                return NetworkResult.Error("Recipes not found.")
//            }
//
//            response.isSuccessful -> {
//                val foodRecipes = response.body()
//                return NetworkResult.Success(foodRecipes!!)
//            }
//
//            else -> {
//                return NetworkResult.Error(response.message())
//            }
//        }
//    }

}


