package ru.vdh.foodrecipes.foodjoke.datasource.mapper

import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeTextDataModel
import ru.vdh.foodrecipes.network.model.FoodJokeRemoteDataSourceModel

class FoodJokeTextDataSourceToDataMapper {

    fun toData(input: FoodJokeRemoteDataSourceModel) =
        FoodJokeTextDataModel(
            input.text
        )

}