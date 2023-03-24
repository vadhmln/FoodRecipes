package ru.vdh.foodrecipes.recipedetails.data.mapper

import ru.vdh.foodrecipes.recipedetails.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipedetails.data.model.ResultDataModel
import ru.vdh.foodrecipes.recipedetails.domain.model.ExtendedIngredientDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.ResultDomainModel

class RecipesDataToDomainMapper {

    fun toDomain(input: ResultDataModel?) =
        input?.let {
            ResultDomainModel(
                it.aggregateLikes,
                input.cheap,
                input.dairyFree,
                input.extendedIngredients.map {
                    ExtendedIngredientDomainModel(
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

//    fun toDomain(input: RecipesDataModel?) =
//        input?.results?.let {
//            RecipesDomainModel(it.map { result ->
//                ResultDomainModel(
//                    result.aggregateLikes,
//                    result.cheap,
//                    result.dairyFree,
//                    result.extendedIngredients.map {
//                        ExtendedIngredientDomainModel(
//                            it.amount,
//                            it.consistency,
//                            it.image,
//                            it.name,
//                            it.original,
//                            it.unit
//                        )
//                    },
//                    result.glutenFree,
//                    result.recipeId,
//                    result.image,
//                    result.readyInMinutes,
//                    result.sourceName,
//                    result.sourceUrl,
//                    result.summary,
//                    result.title,
//                    result.vegan,
//                    result.vegetarian,
//                    result.veryHealthy,
//                )
//            })
//        }
}