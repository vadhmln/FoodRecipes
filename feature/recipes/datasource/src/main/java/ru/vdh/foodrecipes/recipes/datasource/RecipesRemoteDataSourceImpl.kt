package ru.vdh.foodrecipes.recipes.datasource

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.map
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.sandwich.toFlow
import com.skydoves.sandwich.onFailure
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import ru.vdh.foodrecipes.network.FoodRecipesApi
import ru.vdh.foodrecipes.network.NetworkResult
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.datasource.mapper.ErrorResponseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.JokesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper

class RecipesRemoteDataSourceImpl(
    private val foodRecipesApi: FoodRecipesApi,
    private val recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
    private val jokesRemoteDataSourceToDataMapper: JokesRemoteDataSourceToDataMapper
) : RecipesRemoteDataSource {

    @WorkerThread
    override suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        val response = foodRecipesApi.getRecipes(queries)

        response.suspendOnSuccess {
            val list = recipesRemoteDataSourceToDataMapper.toData(data)
            emit(list)
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


