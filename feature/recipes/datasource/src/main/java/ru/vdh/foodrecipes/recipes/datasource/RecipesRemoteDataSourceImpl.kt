package ru.vdh.foodrecipes.recipes.datasource

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flatMap
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
import ru.vdh.foodrecipes.recipes.datasource.mapper.ErrorResponseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataModelToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper
import timber.log.Timber

class RecipesRemoteDataSourceImpl(
    private val foodRecipesApi: FoodRecipesApi,
    private val recipesDao: RecipesDao,
    private val recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
    private val recipesDatabaseToDataMapper: RecipesDatabaseToDataMapper,
    private val recipesDataModelToDatabaseMapper: RecipesDataModelToDatabaseMapper,
    private val errorResponseToDataMapper: ErrorResponseToDataMapper,
) : RecipesRemoteDataSource {

    @WorkerThread
    override suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {

        val recipes = recipesDao.readRecipes()

        if (recipes.isEmpty()) {

            val response = foodRecipesApi.getRecipes(queries)

            Log.d("RecipesRemoteDataSourceImpl", "foodRecipesApi called!")

            response.suspendOnSuccess {
                val recipe = recipesRemoteDataSourceToDataMapper.toData(data)
                val recipeEntity =
                    RecipesEntity(recipesDataModelToDatabaseMapper.toDatabase(recipe))
                recipesDao.insertRecipes(recipeEntity)
                emit(recipe)

            }.suspendOnError {
                map(ErrorResponseMapper) {
                    onError("[Code: $code]: $message")
                    Timber.d("[Code: $code]: $message")

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
                Timber.d(message())
            }.suspendOnException { error(message()) }
        } else {
            emit(recipesDatabaseToDataMapper.toData(recipesDao.readRecipes()))
            Log.d("RecipesRemoteDataSourceImpl", "recipesDao called!")
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
}


