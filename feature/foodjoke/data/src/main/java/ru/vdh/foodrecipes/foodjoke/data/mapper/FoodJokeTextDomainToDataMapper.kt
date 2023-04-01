package ru.vdh.foodrecipes.foodjoke.data.mapper

import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeTextDataModel
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeTextDomainModel

class FoodJokeTextDomainToDataMapper {

    fun toData(input: FoodJokeTextDataModel) =
        FoodJokeTextDomainModel(
            input.text
        )
}