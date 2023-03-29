package ru.vdh.foodrecipes.favoriterecipes.datasource.mapper

import ru.vdh.foodrecipes.database.entities.ExtendedIngredientsEntity
import ru.vdh.foodrecipes.database.entities.FavoritesEntity
import ru.vdh.foodrecipes.database.entities.ResultEntity
import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel

class FavoriteRecipeDataToDatabaseMapper {

    fun toDatabase(input: FavoritesDataModel) =
        FavoritesEntity(
            input.id,
            ResultEntity(
                input.result.aggregateLikes,
                input.result.cheap,
                input.result.dairyFree,
                input.result.extendedIngredients.map {
                    ExtendedIngredientsEntity(
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