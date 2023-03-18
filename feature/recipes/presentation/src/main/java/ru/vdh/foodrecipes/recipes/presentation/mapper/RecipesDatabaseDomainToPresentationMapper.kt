package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.ExtendedIngredientPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.ResultPresentationModel

class RecipesDatabaseDomainToPresentationMapper {

    fun toPresentation(input: List<RecipesDomainModel>) =
        RecipesPresentationModel(
            input.flatMap { it ->
                it.results.map { resultDomainModel ->
                    ResultPresentationModel(
                        resultDomainModel.aggregateLikes,
                        resultDomainModel.cheap,
                        resultDomainModel.dairyFree,
                        resultDomainModel.extendedIngredients.map {
                            ExtendedIngredientPresentationModel(
                                it.amount,
                                it.consistency,
                                it.image,
                                it.name,
                                it.original,
                                it.unit
                            )
                        },
                        resultDomainModel.glutenFree,
                        resultDomainModel.recipeId,
                        resultDomainModel.image,
                        resultDomainModel.readyInMinutes,
                        resultDomainModel.sourceName,
                        resultDomainModel.sourceUrl,
                        resultDomainModel.summary,
                        resultDomainModel.title,
                        resultDomainModel.vegan,
                        resultDomainModel.vegetarian,
                        resultDomainModel.veryHealthy,
                    )
                }
            })

//    fun toPresentation(input: RecipesDomainModel) =
//        RecipesPresentationModel(
//            input.results.map { resultDomainModel ->
//                ResultPresentationModel(
//                    resultDomainModel.aggregateLikes,
//                    resultDomainModel.cheap,
//                    resultDomainModel.dairyFree,
//                    resultDomainModel.extendedIngredients.map {
//                        ExtendedIngredientPresentationModel(
//                            it.amount,
//                            it.consistency,
//                            it.image,
//                            it.name,
//                            it.original,
//                            it.unit
//                        )
//                    },
//                    resultDomainModel.glutenFree,
//                    resultDomainModel.recipeId,
//                    resultDomainModel.image,
//                    resultDomainModel.readyInMinutes,
//                    resultDomainModel.sourceName,
//                    resultDomainModel.sourceUrl,
//                    resultDomainModel.summary,
//                    resultDomainModel.title,
//                    resultDomainModel.vegan,
//                    resultDomainModel.vegetarian,
//                    resultDomainModel.veryHealthy,
//                )
//            })

}
