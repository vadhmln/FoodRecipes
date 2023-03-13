package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.network.FoodRecipesApi
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.datasource.RecipesRemoteDataSourceImpl
import ru.vdh.foodrecipes.recipes.datasource.mapper.JokesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataToDataSourceMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipesDataSourceModule {

    @Provides
    fun providesJokesRemoteDataSourceToDataMapper() = JokesRemoteDataSourceToDataMapper()

    @Provides
    fun providesRecipesDataToDataSourceMapper() = RecipesDataToDataSourceMapper()

    @Provides
    fun providesRecipesRemoteDataSourceToDataMapper() = RecipesRemoteDataSourceToDataMapper()

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(
        foodRecipesApi: FoodRecipesApi,
        recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
        jokesRemoteDataSourceToDataMapper: JokesRemoteDataSourceToDataMapper
    ): RecipesRemoteDataSource = RecipesRemoteDataSourceImpl(
        foodRecipesApi,
        recipesRemoteDataSourceToDataMapper,
        jokesRemoteDataSourceToDataMapper
    )
}