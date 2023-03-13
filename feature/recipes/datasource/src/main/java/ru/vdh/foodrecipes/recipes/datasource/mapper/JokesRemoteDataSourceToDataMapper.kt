package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.network.model.FoodJokeRemoteDataSourceModel
import ru.vdh.foodrecipes.recipes.data.model.FoodJokeDataModel

class JokesRemoteDataSourceToDataMapper {

    fun toData(input: FoodJokeRemoteDataSourceModel) = FoodJokeDataModel(input.text)
}