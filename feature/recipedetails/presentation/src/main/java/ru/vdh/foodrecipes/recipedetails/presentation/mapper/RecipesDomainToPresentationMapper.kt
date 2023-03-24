package ru.vdh.foodrecipes.recipedetails.presentation.mapper

import ru.vdh.foodrecipes.recipedetails.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.ResultDomainModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.ExtendedIngredientPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.RecipesPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.ResultPresentationModel

class RecipesDomainToPresentationMapper {

    fun toPresentation(input: ResultDomainModel?) =
        input?.let {
            ResultPresentationModel(
                it.aggregateLikes,
                input.cheap,
                input.dairyFree,
                input.extendedIngredients.map {
                    ExtendedIngredientPresentationModel(
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
//    fun toPresentation(input: RecipesDomainModel?) =
//        input?.results?.let {
//            RecipesPresentationModel(it.map { result ->
//                ResultPresentationModel(
//                    result.aggregateLikes,
//                    result.cheap,
//                    result.dairyFree,
//                    result.extendedIngredients.map {
//                        ExtendedIngredientPresentationModel(
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