package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.database.entities.ExtendedIngredientsEntity
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.database.entities.ResultEntity
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel
import ru.vdh.foodrecipes.recipes.data.model.ExtendedIngredientDataModel
import ru.vdh.foodrecipes.recipes.data.model.ResultDataModel

class ResultDataToDatabaseMapper {
    private var aggregateLikes: Int = 0
    private var cheap: Boolean = false
    private var dairyFree: Boolean = false
    private var extendedIngredients: List<ExtendedIngredientsEntity> = listOf()
    private var glutenFree: Boolean = false
    private var recipeId: Int = 0
    private var image: String = ""
    private var readyInMinutes: Int = 0
    private var sourceName: String? = ""
    private var sourceUrl: String = ""
    private var summary: String = ""
    var title: String = ""
    private var vegan: Boolean = false
    private var vegetarian: Boolean = false
    private var veryHealthy: Boolean = false

    fun toDatabase(input: ResultRemoteDataSourceModel) =
        ResultEntity(
            input.aggregateLikes,
            input.cheap,
            input.dairyFree,
            input.extendedIngredients.map {
                ExtendedIngredientsEntity(
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

//    fun toDatabase(input: RecipesEntity): ResultEntity {
//        input.foodRecipe.results.map {
//            aggregateLikes = it.aggregateLikes
//            cheap = it.cheap
//            dairyFree = it.dairyFree
//            extendedIngredients = it.extendedIngredients.map {
//                ExtendedIngredientsEntity(
//                    it.amount,
//                    it.consistency,
//                    it.image,
//                    it.name,
//                    it.original,
//                    it.unit
//                )
//            }
//            glutenFree = it.glutenFree
//            recipeId = it.recipeId
//            image = it.image
//            readyInMinutes = it.readyInMinutes
//            sourceName = it.sourceName
//            sourceUrl = it.sourceUrl
//            summary = it.summary
//            title = it.title
//            vegan = it.vegan
//            vegetarian = it.vegetarian
//            veryHealthy = it.veryHealthy
//        }
//
//        return ResultEntity(
//            aggregateLikes,
//            cheap,
//            dairyFree,
//            extendedIngredients,
//            glutenFree,
//            recipeId,
//            image,
//            readyInMinutes,
//            sourceName,
//            sourceUrl,
//            summary,
//            title,
//            vegan,
//            vegetarian,
//            veryHealthy,
//        )
//    }
}
