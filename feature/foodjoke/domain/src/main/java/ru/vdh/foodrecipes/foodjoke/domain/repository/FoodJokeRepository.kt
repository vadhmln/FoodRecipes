package ru.vdh.foodrecipes.foodjoke.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeTextDomainModel

interface FoodJokeRepository {

    fun readFoodJoke(): Flow<List<FoodJokeDomainModel>>

    suspend fun getFoodJoke(apiKey: String): Flow<FoodJokeTextDomainModel>
}