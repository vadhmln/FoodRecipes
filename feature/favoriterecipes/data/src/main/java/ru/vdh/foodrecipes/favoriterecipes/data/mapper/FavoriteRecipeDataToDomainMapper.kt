package ru.vdh.foodrecipes.favoriterecipes.data.mapper

import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.ExtendedIngredientsDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.ResultDomainModel

class FavoriteRecipeDataToDomainMapper {

    fun toDomain(input: FavoritesDataModel) =
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