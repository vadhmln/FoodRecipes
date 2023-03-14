package ru.vdh.foodrecipes.recipes.datasource.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.recipes.data.model.ExtendedIngredientDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.data.model.ResultDataModel

class RecipesDatabaseToDataMapper {

    suspend fun toData(input: Flow<List<RecipesEntity>>) =
            RecipesDataModel(
                input.toList().flatMap { recipesEntityList ->
                    recipesEntityList.flatMap { recipesEntity ->
                        recipesEntity.foodRecipe.results.map {
                                result ->
                            ResultDataModel(
                                result.aggregateLikes,
                                result.cheap,
                                result.dairyFree,
                                result.extendedIngredients.map {
                                    ExtendedIngredientDataModel(
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
                    }
                }
            )

}