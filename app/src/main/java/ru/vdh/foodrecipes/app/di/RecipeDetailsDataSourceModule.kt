package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.datasource.RecipeDetailsDataSourceImpl
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.FavoriteRecipeDataToDatabaseMapper
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.FavoriteRecipeDatabaseToDataMapper
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.RecipesLocalDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.ResultDataToDatabaseMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDetailsDataSourceModule {

    @Provides
    fun providesRecipesLocalDatabaseToDataMapper() = RecipesLocalDatabaseToDataMapper()

    @Provides
    fun providesResultDataToDatabaseMapper() = ResultDataToDatabaseMapper()

    @Provides
    fun providesFavoriteRecipeDataToDatabaseMapper() = FavoriteRecipeDataToDatabaseMapper()

    @Provides
    fun providesFavoriteRecipeDatabaseToDataMapper() = FavoriteRecipeDatabaseToDataMapper()

    @Provides
    @Singleton
    fun providesRecipeDetailsDataSource(
        recipesDao: RecipesDao,
        recipesLocalDatabaseToDataMapper: RecipesLocalDatabaseToDataMapper,
        favoriteRecipeDataToDatabaseMapper: FavoriteRecipeDataToDatabaseMapper,
        favoriteRecipeDatabaseToDataMapper: FavoriteRecipeDatabaseToDataMapper,
    ): RecipeDetailsDataSource = RecipeDetailsDataSourceImpl(
        recipesDao,
        recipesLocalDatabaseToDataMapper,
        favoriteRecipeDataToDatabaseMapper,
        favoriteRecipeDatabaseToDataMapper,
    )
}