package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.network.model.ExtendedIngredientRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel

class RecipesDataToDataSourceMapper {

    fun toDataSource(input: RecipesDataModel) =
        RecipesRemoteDataSourceModel(input.results.map { resultDataModel ->
            ResultRemoteDataSourceModel(
                resultDataModel.aggregateLikes,
                resultDataModel.cheap,
                resultDataModel.dairyFree,
                resultDataModel.extendedIngredients.map {
                    ExtendedIngredientRemoteDataSourceModel(
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
        })
}