package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.datasource.RecipeDetailsDataSourceImpl
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
    @Singleton
    fun provideLocalDataSource(
        recipesDao: RecipesDao,
        recipesLocalDatabaseToDataMapper: RecipesLocalDatabaseToDataMapper
    ): RecipeDetailsDataSource = RecipeDetailsDataSourceImpl(
        recipesDao,
        recipesLocalDatabaseToDataMapper
    )
}