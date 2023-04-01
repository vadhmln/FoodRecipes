package ru.vdh.foodrecipes.foodjoke.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.foodjoke.data.datasource.FoodJokeDataSource
import ru.vdh.foodrecipes.foodjoke.data.mapper.FoodJokeDataToDomainMapper
import ru.vdh.foodrecipes.foodjoke.data.mapper.FoodJokeTextDomainToDataMapper
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeTextDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.repository.FoodJokeRepository

class FoodJokeRepositoryImpl(
    private val foodJokeDataSource: FoodJokeDataSource,
    private val foodJokeDataToDomainMapper: FoodJokeDataToDomainMapper,
    private val foodJokeTextDomainToDataMapper: FoodJokeTextDomainToDataMapper,
) : FoodJokeRepository {

    override fun readFoodJoke(): Flow<List<FoodJokeDomainModel>> =
        foodJokeDataSource.readFoodJoke().map { list ->
            list.map(foodJokeDataToDomainMapper::toDomain)
        }

    override suspend fun getFoodJoke(apiKey: String): Flow<FoodJokeTextDomainModel> =
        foodJokeDataSource.getFoodJoke(apiKey).map(foodJokeTextDomainToDataMapper::toData)
}


