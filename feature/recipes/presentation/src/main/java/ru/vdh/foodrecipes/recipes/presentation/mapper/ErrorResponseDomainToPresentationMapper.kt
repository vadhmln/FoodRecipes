package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.RecipeErrorMassageDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipeErrorResponsePresentationModel

class ErrorResponseDomainToPresentationMapper {

    fun toPresentation(input: RecipeErrorMassageDomainModel) =
        RecipeErrorResponsePresentationModel(
            code = input.code,
            message = input.message
        )

}