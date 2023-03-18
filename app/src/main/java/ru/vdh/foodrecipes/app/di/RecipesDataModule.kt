package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.recipes.data.datasource.DataStoreDataSource
import ru.vdh.foodrecipes.recipes.data.datasource.LocalDataSource
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.DataStoreDataToDomainMapper
import ru.vdh.foodrecipes.recipes.data.mapper.ErrorResponseToDomainMapper
import ru.vdh.foodrecipes.recipes.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipes.data.repository.DataStoreRepositoryImpl
import ru.vdh.foodrecipes.recipes.data.repository.RecipesRepositoryImpl
import ru.vdh.foodrecipes.recipes.domain.repository.DataStoreRepository
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository
import ru.vdh.foodrecipes.recipes.presentation.mapper.DataStoreDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.ErrorResponseDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesDatabaseDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesPresentationToDomainMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipesDataModule {

    @Provides
    fun providesRecipesDataToDomainMapper() = RecipesDataToDomainMapper()

    @Provides
    fun providesRecipesPresentationToDomainMapper() = RecipesPresentationToDomainMapper()

    @Provides
    fun providesRecipesDomainToPresentationMapper() = RecipesDomainToPresentationMapper()

    @Provides
    fun providesErrorResponseDomainToPresentationMapper() =
        ErrorResponseDomainToPresentationMapper()

    @Provides
    fun providesDataStoreDomainToPresentationMapper() = DataStoreDomainToPresentationMapper()

    @Provides
    fun providesRecipesDatabaseDomainToPresentationMapper() = RecipesDatabaseDomainToPresentationMapper()

    @Provides
    fun providesErrorResponseToDomainMapper() = ErrorResponseToDomainMapper()

    @Provides
    fun providesDataStoreDataToDomainMapper() = DataStoreDataToDomainMapper()

    @Provides
    @Singleton
    fun provideRecipesRemoteRepository(
        recipesRemoteDataSource: RecipesRemoteDataSource,
        localDataSource: LocalDataSource,
        recipesDataToDomainMapper: RecipesDataToDomainMapper,
        errorResponseToDomainMapper: ErrorResponseToDomainMapper,
    ): RecipesRepository = RecipesRepositoryImpl(
        recipesRemoteDataSource = recipesRemoteDataSource,
        localDataSource,
        recipesDataToDomainMapper,
        errorResponseToDomainMapper
    )

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStoreDataSource: DataStoreDataSource,
        dataStoreDataToDomainMapper: DataStoreDataToDomainMapper,
    ): DataStoreRepository = DataStoreRepositoryImpl(
        dataStoreDataSource,
        dataStoreDataToDomainMapper,
    )
}