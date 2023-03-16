package ru.vdh.foodrecipes.recipes.data.mapper

import ru.vdh.foodrecipes.recipes.data.model.RecipeErrorMassageDataModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipeErrorMassageDomainModel

class ErrorResponseToDomainMapper {

    fun toDomain(input: RecipeErrorMassageDataModel) =
        RecipeErrorMassageDomainModel(
            code = input.code,
            message = input.message
        )

}