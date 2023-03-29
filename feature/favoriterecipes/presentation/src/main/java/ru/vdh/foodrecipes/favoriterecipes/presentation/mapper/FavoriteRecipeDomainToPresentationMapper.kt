package ru.vdh.foodrecipes.favoriterecipes.presentation.mapper

import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.ExtendedIngredientsPresentationModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.ResultPresentationModel

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