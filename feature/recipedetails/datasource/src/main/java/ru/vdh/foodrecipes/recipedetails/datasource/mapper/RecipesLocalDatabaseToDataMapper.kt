package ru.vdh.foodrecipes.recipedetails.datasource.mapper

import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.database.entities.ResultEntity
import ru.vdh.foodrecipes.recipedetails.data.model.ExtendedIngredientDataModel
import ru.vdh.foodrecipes.recipedetails.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipedetails.data.model.ResultDataModel

class RecipesLocalDatabaseToDataMapper {

    fun toData(input: ResultEntity?) =
        input?.let {
            ResultDataModel(
                it.aggregateLikes,
                input.cheap,
                input.dairyFree,
                input. extendedIngredients.map {
                    ExtendedIngredientDataModel(
                        it.amount,
                        it.consistency,
                        it.image,
                        it.name,
                        it.original,
                        it.unit
                    )
                },
                input.glutenFree,
                input.recipeId,
                input.image,
                input.readyInMinutes,
                input.sourceName,
                input.sourceUrl,
                input.summary,
                input.title,
                input.vegan,
                input.vegetarian,
                input.veryHealthy,

            )
        }
//    fun toData(input: RecipesEntity?) =
//        input?.foodRecipe?.results?.let { list ->
//            RecipesDataModel(
//                list.map  { it ->
//                    ResultDataModel(
//                        it.aggregateLikes,
//                        it.cheap,
//                        it.dairyFree,
//                        it.extendedIngredients.map {
//                            ExtendedIngredientDataModel(
//                                it.amount,
//                                it.consistency,
//                                it.image,
//                                it.name,
//                                it.original,
//                                it.unit
//                            )
//                        },
//                        it.glutenFree,
//                        it.recipeId,
//                        it.image,
//                        it.readyInMinutes,
//                        it.sourceName,
//                        it.sourceUrl,
//                        it.summary,
//                        it.title,
//                        it.vegan,
//                        it.vegetarian,
//                        it.veryHealthy,
//                    )
//                })
//        }
}
