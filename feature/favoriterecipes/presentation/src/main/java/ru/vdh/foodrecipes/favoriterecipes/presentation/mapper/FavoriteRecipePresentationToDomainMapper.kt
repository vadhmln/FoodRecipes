package ru.vdh.foodrecipes.favoriterecipes.presentation.mapper

import ru.vdh.foodrecipes.favoriterecipes.domain.model.ExtendedIngredientsDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.ResultDomainModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoritesPresentationModel


class FavoriteRecipePresentationToDomainMapper {

    fun toDomain(input: FavoritesPresentationModel) =
        FavoritesDomainModel(
            input.id,
            ResultDomainModel(
                input.result.aggregateLikes,
                input.result.cheap,
                input.result.dairyFree,
                input.result.extendedIngredients.map {
                    ExtendedIngredientsDomainModel(
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