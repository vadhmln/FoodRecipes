package ru.vdh.foodrecipes.recipedetails.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.model.FavoritesDataModel
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.FavoriteRecipeDataToDatabaseMapper
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.FavoriteRecipeDatabaseToDataMapper
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.RecipesLocalDatabaseToDataMapper

class RecipeDetailsDataSourceImpl(
    private val recipesDao: RecipesDao,
    private val recipesLocalDatabaseToDataMapper: RecipesLocalDatabaseToDataMapper,
    private val favoriteRecipeDataToDatabaseMapper: FavoriteRecipeDataToDatabaseMapper,
    private val favoriteRecipeDatabaseToDataMapper: FavoriteRecipeDatabaseToDataMapper,

    ) : RecipeDetailsDataSource {
    override fun getItemById(toDoId: Int?) =
        recipesDao.getItemById(toDoId).map(recipesLocalDatabaseToDataMapper::toData)

    override suspend fun insertFavoriteRecipes(favoriteRecipe: FavoritesDataModel) {
        recipesDao.insertFavoriteRecipe(favoriteRecipeDataToDatabaseMapper.toDatabase(favoriteRecipe))
    }

    override fun getFavoriteRecipes(): Flow<List<FavoritesDataModel>> =
        recipesDao.readFavoriteRecipes().map { list ->
            list.map(favoriteRecipeDatabaseToDataMapper::toData)
        }

    override suspend fun deleteFavoriteRecipe(favoriteRecipe: FavoritesDataModel) {
        recipesDao.deleteFavoriteRecipe(favoriteRecipeDataToDatabaseMapper.toDatabase(favoriteRecipe))
    }

}