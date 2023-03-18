package ru.vdh.foodrecipes.recipes.datasource.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
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

//    fun toData(input: RecipesEntity) =
//        RecipesDataModel(
//            input.foodRecipe.results.map  { it ->
//                ResultDataModel(
//                    it.aggregateLikes,
//                    it.cheap,
//                    it.dairyFree,
//                    it.extendedIngredients.map {
//                        ExtendedIngredientDataModel(
//                            it.amount,
//                            it.consistency,
//                            it.image,
//                            it.name,
//                            it.original,
//                            it.unit
//                        )
//                    },
//                    it.glutenFree,
//                    it.recipeId,
//                    it.image,
//                    it.readyInMinutes,
//                    it.sourceName,
//                    it.sourceUrl,
//                    it.summary,
//                    it.title,
//                    it.vegan,
//                    it.vegetarian,
//                    it.veryHealthy,
//                )
//            })
}
