package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.recipes.data.model.RecipeErrorMassageDataModel
import ru.vdh.foodrecipes.recipes.datasource.model.RecipeErrorResponseDataSourceModel

class ErrorResponseToDataMapper {

    fun toData(input: RecipeErrorResponseDataSourceModel) =
        RecipeErrorMassageDataModel(
            code = input.code,
            message = input.message
        )

}