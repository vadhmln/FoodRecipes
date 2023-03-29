package ru.vdh.foodrecipes.favoriterecipes.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel

interface FavoriteRecipeDataSource {
    fun getFavoriteRecipes(): Flow<List<FavoritesDataModel>>
}