package ru.vdh.foodrecipes.foodjoke.datasource.mapper

import ru.vdh.foodrecipes.database.entities.FoodJokeEntity
import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.network.model.FoodJokeRemoteDataSourceModel

class FoodJokeDataToDataSourceMapper {

    fun toDataSource(input: FoodJokeDataModel ) =
        FoodJokeEntity(
            FoodJokeRemoteDataSourceModel(
                input.foodJoke.text
            )
        )
}