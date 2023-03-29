package ru.vdh.foodrecipes.favoriterecipes.datasource.mapper

import ru.vdh.foodrecipes.database.entities.FavoritesEntity
import ru.vdh.foodrecipes.favoriterecipes.data.model.ExtendedIngredientsDataModel
import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.favoriterecipes.data.model.ResultDataModel

class FavoriteRecipeDatabaseToDataMapper {

    fun toData(input: FavoritesEntity ) =
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