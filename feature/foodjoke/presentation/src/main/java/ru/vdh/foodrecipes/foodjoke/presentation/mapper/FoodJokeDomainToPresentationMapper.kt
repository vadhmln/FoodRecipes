package ru.vdh.foodrecipes.foodjoke.presentation.mapper

import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.FoodJokePresentationModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.FoodJokeTextPresentationModel

class FoodJokeDomainToPresentationMapper {

    fun toPresentation(input: FoodJokeDomainModel) =
        FoodJokePresentationModel(
            FoodJokeTextPresentationModel(
                input.foodJoke.text
            )
        )
}