package ru.vdh.foodrecipes.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.network.FoodRecipesApi
import ru.vdh.foodrecipes.recipes.data.datasource.DataStoreDataSource
import ru.vdh.foodrecipes.recipes.data.datasource.LocalDataSource
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.datasource.DataStoreDataSourceImpl
import ru.vdh.foodrecipes.recipes.datasource.LocalDataSourceImpl
import ru.vdh.foodrecipes.recipes.datasource.RecipesRemoteDataSourceImpl
import ru.vdh.foodrecipes.recipes.datasource.mapper.ErrorResponseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.JokesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataModelToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesLocalDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesRemoteDataSourceToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.ResultDataToDatabaseMapper
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
    fun providesRecipesLocalDatabaseToDataMapper() = RecipesLocalDatabaseToDataMapper()


    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(
        foodRecipesApi: FoodRecipesApi,
        recipesDao: RecipesDao,
        recipesRemoteDataSourceToDataMapper: RecipesRemoteDataSourceToDataMapper,
        resultDataToDatabaseMapper: ResultDataToDatabaseMapper,
        recipesDataModelToDatabaseMapper: RecipesDataModelToDatabaseMapper,
    ): RecipesRemoteDataSource = RecipesRemoteDataSourceImpl(
        foodRecipesApi,
        recipesDao,
        recipesRemoteDataSourceToDataMapper,
        resultDataToDatabaseMapper,
        recipesDataModelToDatabaseMapper,
    )

    @Provides
    @Singleton
    fun provideDataStoreDataSource(@ApplicationContext context: Context): DataStoreDataSource =
        DataStoreDataSourceImpl(context = context)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        recipesDao: RecipesDao,
        recipesLocalDatabaseToDataMapper: RecipesLocalDatabaseToDataMapper,
        recipesDataToDatabaseMapper: RecipesDataToDatabaseMapper,
    ): LocalDataSource = LocalDataSourceImpl(
        recipesDao,
        recipesLocalDatabaseToDataMapper,
        recipesDataToDatabaseMapper,
    )

}