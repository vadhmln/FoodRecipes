package ru.vdh.foodrecipes.foodjoke.presentation.mapper

import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeTextDomainModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.FoodJokeTextPresentationModel

class FoodJokeTextDomainToPresentationMapper {

    fun toPresentation(input: FoodJokeTextDomainModel) =
        FoodJokeTextPresentationModel(
            input.text
        )
}