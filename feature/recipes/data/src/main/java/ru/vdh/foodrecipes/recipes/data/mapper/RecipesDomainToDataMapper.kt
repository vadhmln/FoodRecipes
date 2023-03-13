package ru.vdh.foodrecipes.recipes.data.mapper

import ru.vdh.foodrecipes.recipes.data.model.ExtendedIngredientDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.data.model.ResultDataModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel

class RecipesDomainToDataMapper {

    fun toData(input: RecipesDomainModel): RecipesDataModel {

        val list = input.results.map { resultDataModel ->
            ResultDataModel(
                resultDataModel.aggregateLikes,
                resultDataModel.cheap,
                resultDataModel.dairyFree,
                resultDataModel.extendedIngredients.map {
                    ExtendedIngredientDataModel(
                        it.amount,
                        it.consistency,
                        it.image,
                        it.name,
                        it.original,
                        it.unit
                    )
                },
                resultDataModel.glutenFree,
                resultDataModel.recipeId,
                resultDataModel.image,
                resultDataModel.readyInMinutes,
                resultDataModel.sourceName,
                resultDataModel.sourceUrl,
                resultDataModel.summary,
                resultDataModel.title,
                resultDataModel.vegan,
                resultDataModel.vegetarian,
                resultDataModel.veryHealthy,
            )
        }

        return RecipesDataModel(list)
    }

}