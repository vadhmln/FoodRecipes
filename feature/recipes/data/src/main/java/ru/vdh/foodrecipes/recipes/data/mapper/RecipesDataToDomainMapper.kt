package ru.vdh.foodrecipes.recipes.data.mapper

import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.domain.model.ExtendedIngredientDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.ResultDomainModel

class RecipesDataToDomainMapper {

    fun toDomain(input: RecipesDataModel) =
        RecipesDomainModel(input.results.map { result ->
            ResultDomainModel(
                result.aggregateLikes,
                result.cheap,
                result.dairyFree,
                result.extendedIngredients.map {
                    ExtendedIngredientDomainModel(
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
        })
}