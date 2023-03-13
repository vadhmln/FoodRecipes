package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.ExtendedIngredientDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.ResultDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel

class RecipesPresentationToDomainMapper {

    fun toDomain(input: RecipesPresentationModel) =
        RecipesDomainModel(input.results.map { resultDataModel ->
            ResultDomainModel(
                resultDataModel.aggregateLikes,
                resultDataModel.cheap,
                resultDataModel.dairyFree,
                resultDataModel.extendedIngredients.map {
                    ExtendedIngredientDomainModel(
                        it.amount,
                        it.consistency,
                        it.image,
                        it.name,
                        it.original,
                        it.unit
                    )
                },
                resultDataModel.glutenFree,
                resultDataModel.recipeId,
                resultDataModel.image,
                resultDataModel.readyInMinutes,
                resultDataModel.sourceName,
                resultDataModel.sourceUrl,
                resultDataModel.summary,
                resultDataModel.title,
                resultDataModel.vegan,
                resultDataModel.vegetarian,
                resultDataModel.veryHealthy,
            )
        }

        )
}
