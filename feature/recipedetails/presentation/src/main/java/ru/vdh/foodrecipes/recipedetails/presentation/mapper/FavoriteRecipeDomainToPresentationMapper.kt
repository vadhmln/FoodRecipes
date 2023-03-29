package ru.vdh.foodrecipes.recipedetails.presentation.mapper

import ru.vdh.foodrecipes.recipedetails.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.ExtendedIngredientsPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.ResultPresentationModel

class FavoriteRecipeDomainToPresentationMapper {

    fun toPresentation(input: FavoritesDomainModel) =
        FavoritesPresentationModel(
            input.id,
            ResultPresentationModel(
                input.result.aggregateLikes,
                input.result.cheap,
                input.result.dairyFree,
                input.result.extendedIngredients.map {
                    ExtendedIngredientsPresentationModel(
                        it.amount,
                        it.consistency,
                        it.image,
                        it.name,
                        it.original,
                        it.unit
                    )
                },
                input.result.glutenFree,
                input.result.recipeId,
                input.result.image,
                input.result.readyInMinutes,
                input.result.sourceName,
                input.result.sourceUrl,
                input.result.summary,
                input.result.title,
                input.result.vegan,
                input.result.vegetarian,
                input.result.veryHealthy,
            )
        )
}