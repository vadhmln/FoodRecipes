package ru.vdh.foodrecipes.recipedetails.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipedetails.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.recipedetails.data.model.ResultDataModel

interface RecipeDetailsDataSource {

    fun getItemById(toDoId: Int?): Flow<ResultDataModel?>

    suspend fun insertFavoriteRecipes(favoriteRecipe: FavoritesDataModel)

    suspend fun deleteFavoriteRecipe(favoriteRecipe: FavoritesDataModel)

    fun getFavoriteRecipes(): Flow<List<FavoritesDataModel>>

}