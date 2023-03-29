package ru.vdh.foodrecipes.favoriterecipes.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel

interface FavoriteRecipeDataSource {

    fun getFavoriteRecipes(): Flow<List<FavoritesDataModel>>

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesDataModel)

    suspend fun deleteAllFavoriteRecipes()
}