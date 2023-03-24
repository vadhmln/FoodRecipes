package ru.vdh.foodrecipes.recipedetails.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipedetails.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipedetails.data.model.ResultDataModel

interface RecipeDetailsDataSource {

    fun getItemById(toDoId: Int?): Flow<ResultDataModel?>

}