package ru.vdh.foodrecipes.recipes.datasource.mapper

import retrofit2.Response
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel
import ru.vdh.foodrecipes.recipes.data.model.ExtendedIngredientDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.data.model.ResultDataModel

class RecipesRemoteDataSourceToDataMapper {

    suspend fun <T : Any> toDataNew(
        execute: () -> Response<T>
    ): Response<T> {
        val response = execute()
        val body = response.body()

        return response
    }

    fun toData(input: RecipesRemoteDataSourceModel) =
        RecipesDataModel(input.resultRemoteDataSourceModels.map { result ->
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
        })


}