package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.network.model.ExtendedIngredientRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel

class RecipesDataToDatabaseMapper {

    fun toDatabase(input: RecipesDataModel) =
        RecipesEntity(
            RecipesRemoteDataSourceModel(
                input.results.map { result ->
                    ResultRemoteDataSourceModel(
                        result.aggregateLikes,
                        result.cheap,
                        result.dairyFree,
                        result.extendedIngredients.map {
                            ExtendedIngredientRemoteDataSourceModel(
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
                }
            )
        )
}