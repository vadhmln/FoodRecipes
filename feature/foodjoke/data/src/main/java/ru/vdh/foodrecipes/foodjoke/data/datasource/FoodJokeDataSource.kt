package ru.vdh.foodrecipes.foodjoke.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeTextDataModel

interface FoodJokeDataSource {

    fun readFoodJoke(): Flow<List<FoodJokeDataModel>>

    suspend fun getFoodJoke(apiKey: String): Flow<FoodJokeTextDataModel>
}