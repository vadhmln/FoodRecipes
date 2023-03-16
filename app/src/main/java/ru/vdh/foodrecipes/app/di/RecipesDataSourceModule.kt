package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.network.FoodRecipesApi
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.datasource.RecipesRemoteDataSourceImpl
import ru.vdh.foodrecipes.recipes.datasource.mapper.ErrorResponseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.JokesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataModelToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipesDataSourceModule {

    @Provides
    fun providesJokesRemoteDataSourceToDataMapper() = JokesRemoteDataSourceToDataMapper()

    @Provides
    fun providesRecipesDataToDataSourceMapper() = RecipesDataToDatabaseMapper()

    @Provides
    fun providesRecipesDatabaseToDataMapper() = RecipesDatabaseToDataMapper()

    @Provides
    fun providesErrorResponseToDataMapper() = ErrorResponseToDataMapper()

    @Provides
    fun providesRecipesDataModelToDatabaseMapper() = RecipesDataModelToDatabaseMapper()

    @Provides
    fun providesRecipesRemoteDataSourceToDataMapper() = RecipesRemoteDataSourceToDataMapper()

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(
        foodRecipesApi: FoodRecipesApi,
        recipesDao: RecipesDao,
        recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
        recipesDatabaseToDataMapper: RecipesDatabaseToDataMapper,
        recipesDataModelToDatabaseMapper: RecipesDataModelToDatabaseMapper,
        errorResponseToDataMapper: ErrorResponseToDataMapper,
    ): RecipesRemoteDataSource = RecipesRemoteDataSourceImpl(
        foodRecipesApi,
        recipesDao,
        recipesRemoteDataSourceToDataMapper,
        recipesDatabaseToDataMapper,
        recipesDataModelToDatabaseMapper,
        errorResponseToDataMapper,
    )
}