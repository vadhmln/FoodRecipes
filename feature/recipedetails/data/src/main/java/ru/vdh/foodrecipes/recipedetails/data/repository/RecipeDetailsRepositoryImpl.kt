package ru.vdh.foodrecipes.recipedetails.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.mapper.FavoriteRecipeDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.data.mapper.FavoriteRecipeDomainToDataMapper
import ru.vdh.foodrecipes.recipedetails.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository

class RecipeDetailsRepositoryImpl(
    private val recipeDetailsDataSource: RecipeDetailsDataSource,
    private val recipesDataToDomainMapper: RecipesDataToDomainMapper,
    private val favoriteRecipeDomainToDataMapper: FavoriteRecipeDomainToDataMapper,
    private val favoriteRecipeDataToDomainMapper: FavoriteRecipeDataToDomainMapper,
) : RecipeDetailsRepository {

    override fun getItemById(recipeId: Int?) =
        recipeDetailsDataSource.getItemById(recipeId).map(recipesDataToDomainMapper::toDomain)

    override suspend fun insertFavoriteRecipes(favoriteRecipe: FavoritesDomainModel) {
        recipeDetailsDataSource.insertFavoriteRecipes(
            favoriteRecipeDomainToDataMapper.toData(favoriteRecipe)
        )
    }

    override fun getFavoriteRecipes(): Flow<List<FavoritesDomainModel>> =
        recipeDetailsDataSource.getFavoriteRecipes().map { list ->
            list.map(favoriteRecipeDataToDomainMapper::toDomain)
        }

    override suspend fun deleteFavoriteRecipe(favoriteRecipe: FavoritesDomainModel) {
        recipeDetailsDataSource.deleteFavoriteRecipe(
            favoriteRecipeDomainToDataMapper.toData(favoriteRecipe)
        )
    }
}


