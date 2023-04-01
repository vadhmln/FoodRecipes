package ru.vdh.foodrecipes.foodjoke.data.mapper

import ru.vdh.foodrecipes.foodjoke.data.model.FoodJokeDataModel
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeTextDomainModel

class FoodJokeDataToDomainMapper {

    fun toDomain(input: FoodJokeDataModel) =
        FoodJokeDomainModel(
            FoodJokeTextDomainModel(
                input.foodJoke.text
            )
        )
}