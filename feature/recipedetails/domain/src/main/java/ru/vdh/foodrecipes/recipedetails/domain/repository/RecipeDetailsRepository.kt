package ru.vdh.foodrecipes.recipedetails.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipedetails.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.ResultDomainModel

interface RecipeDetailsRepository {

    fun getItemById(recipeId: Int?): Flow<ResultDomainModel?>

    suspend fun insertFavoriteRecipes(favoriteRecipe: FavoritesDomainModel)

    suspend fun deleteFavoriteRecipe(favoriteRecipe: FavoritesDomainModel)

    fun getFavoriteRecipes(): Flow<List<FavoritesDomainModel>>

}