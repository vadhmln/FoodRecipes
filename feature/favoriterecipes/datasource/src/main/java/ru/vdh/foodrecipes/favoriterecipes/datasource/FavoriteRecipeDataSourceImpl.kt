package ru.vdh.foodrecipes.favoriterecipes.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.favoriterecipes.data.datasource.FavoriteRecipeDataSource
import ru.vdh.foodrecipes.favoriterecipes.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.favoriterecipes.datasource.mapper.FavoriteRecipeDataToDatabaseMapper
import ru.vdh.foodrecipes.favoriterecipes.datasource.mapper.FavoriteRecipeDatabaseToDataMapper

class FavoriteRecipeDataSourceImpl(
    private val recipesDao: RecipesDao,
    private val favoriteRecipeDataToDatabaseMapper: FavoriteRecipeDataToDatabaseMapper,
    private val favoriteRecipeDatabaseToDataMapper: FavoriteRecipeDatabaseToDataMapper,
    ) : FavoriteRecipeDataSource {
    override fun getFavoriteRecipes(): Flow<List<FavoritesDataModel>> =
        recipesDao.readFavoriteRecipes().map { list ->
            list.map(favoriteRecipeDatabaseToDataMapper::toData)
        }


}