package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.recipes.data.model.ExtendedIngredientDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.data.model.ResultDataModel

class RecipesDatabaseToDataMapper {

    fun toData(input: List<RecipesEntity>) =
        RecipesDataModel(
            input.flatMap { result ->
                result.foodRecipe.results.map { it ->
                    ResultDataModel(
                        it.aggregateLikes,
                        it.cheap,
                        it.dairyFree,
                        it.extendedIngredients.map {
                            ExtendedIngredientDataModel(
                                it.amount,
                                it.consistency,
                                it.image,
                                it.name,
                                it.original,
                                it.unit
                            )
                        },
                        it.glutenFree,
                        it.recipeId,
                        it.image,
                        it.readyInMinutes,
                        it.sourceName,
                        it.sourceUrl,
                        it.summary,
                        it.title,
                        it.vegan,
                        it.vegetarian,
                        it.veryHealthy,
                    )
                }
            })
}


//    suspend fun toData(input: Flow<List<RecipesEntity>>) =
//        RecipesDataModel(
//            input.toList().flatMap { recipesEntityList ->
//                recipesEntityList.flatMap { recipesEntity ->
//                    recipesEntity.foodRecipe.results.map {
//                            result ->
//                        ResultDataModel(
//                            result.aggregateLikes,
//                            result.cheap,
//                            result.dairyFree,
//                            result.extendedIngredients.map {
//                                ExtendedIngredientDataModel(
//                                    it.amount,
//                                    it.consistency,
//                                    it.image,
//                                    it.name,
//                                    it.original,
//                                    it.unit
//                                )
//                            },
//                            result.glutenFree,
//                            result.recipeId,
//                            result.image,
//                            result.readyInMinutes,
//                            result.sourceName,
//                            result.sourceUrl,
//                            result.summary,
//                            result.title,
//                            result.vegan,
//                            result.vegetarian,
//                            result.veryHealthy,
//                        )
//                    }
//                }
//            }
//        )

//}