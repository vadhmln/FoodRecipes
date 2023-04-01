package ru.vdh.foodrecipes.foodjoke.datasource.mapper

import ru.vdh.foodrecipes.database.entities.FoodJokeEntity
import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeTextDataModel

class FoodJokeDataSourceToDataMapper {

    fun toData(input: FoodJokeEntity) =
        FoodJokeDataModel(
            FoodJokeTextDataModel(
                input.foodJoke.text
            )
        )
}