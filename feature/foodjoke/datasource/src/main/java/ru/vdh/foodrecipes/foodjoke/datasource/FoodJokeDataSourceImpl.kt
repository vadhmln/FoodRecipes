package ru.vdh.foodrecipes.foodjoke.datasource

import android.util.Log
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.foodjoke.data.datasource.FoodJokeDataSource
import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.foodjoke.datasource.mapper.FoodJokeDataSourceToDataMapper
import ru.vdh.foodrecipes.foodjoke.datasource.mapper.FoodJokeDataToDataSourceMapper
import ru.vdh.foodrecipes.foodjoke.datasource.mapper.FoodJokeTextDataSourceToDataMapper
import ru.vdh.foodrecipes.network.FoodRecipesApi

class FoodJokeDataSourceImpl(
    private val foodRecipesApi: FoodRecipesApi,
    private val recipesDao: RecipesDao,
    private val foodJokeDataSourceToDataMapper: FoodJokeDataSourceToDataMapper,
    private val foodJokeTextDataSourceToDataMapper: FoodJokeTextDataSourceToDataMapper,
    private val foodJokeDataToDataSourceMapper: FoodJokeDataToDataSourceMapper,
) : FoodJokeDataSource {

    override fun readFoodJoke(): Flow<List<FoodJokeDataModel>> =
        recipesDao.readFoodJoke().map { list ->
            list.map(foodJokeDataSourceToDataMapper::toData)
        }

    override suspend fun getFoodJoke(apiKey: String) = flow {

        val response = foodRecipesApi.getFoodJoke(apiKey)

        response.suspendOnSuccess {
            val foodJokeText = foodJokeTextDataSourceToDataMapper.toData(data)

            val foodJoke = FoodJokeDataModel(foodJokeText)
            recipesDao.insertFoodJoke(foodJokeDataToDataSourceMapper.toDataSource(foodJoke))
            emit(foodJokeText)

            Log.d("FoodJokeDataSourceImpl", "FoodJokeDataSourceImpl called!!!")
        }.suspendOnError {
            Log.d("onError", message())
        }.onFailure {

            Log.d("onError", message())
        }.suspendOnException { error(message()) }
    }.flowOn(Dispatchers.IO)

}