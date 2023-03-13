package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.ExtendedIngredientPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.ResultPresentationModel

class RecipesDomainToPresentationMapper {

    fun toPresentation(input: RecipesDomainModel) =
        RecipesPresentationModel(input.results.map { result ->
            ResultPresentationModel(
                result.aggregateLikes,
                result.cheap,
                result.dairyFree,
                result.extendedIngredients.map {
                    ExtendedIngredientPresentationModel(
                        it.amount,
                        it.consistency,
                        it.image,
                        it.name,
                        it.original,
                        it.unit
                    )
                },
                result.glutenFree,
                result.recipeId,
                result.image,
                result.readyInMinutes,
                result.sourceName,
                result.sourceUrl,
                result.summary,
                result.title,
                result.vegan,
                result.vegetarian,
                result.veryHealthy,
            )
        })
}