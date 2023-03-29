package ru.vdh.foodrecipes.favoriterecipes.data.mapper

import ru.vdh.foodrecipes.favoriterecipes.data.model.ExtendedIngredientsDataModel
import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.favoriterecipes.data.model.ResultDataModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel

class FavoriteRecipeDomainToDataMapper {

    fun toData(input: FavoritesDomainModel) =
        FavoritesDataModel(
            input.id,
            ResultDataModel(
                input.result.aggregateLikes,
                input.result.cheap,
                input.result.dairyFree,
                input.result.extendedIngredients.map {
                    ExtendedIngredientsDataModel(
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