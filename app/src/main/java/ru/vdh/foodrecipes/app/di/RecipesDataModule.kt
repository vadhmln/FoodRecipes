package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipes.data.repository.RecipesRepositoryImpl
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesPresentationToDomainMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipesDataModule {

    @Provides
    fun providesNewFeatureDataModelToDataSourceMapper() = RecipesDataToDomainMapper()

    @Provides
    fun providesNewFeaturePresentationToDomainMapper() = RecipesPresentationToDomainMapper()

    @Provides
    fun providesNewFeatureDomainToPresentationMapper() = RecipesDomainToPresentationMapper()

    @Provides
    @Singleton
    fun provideNewFeatureRepository(
        recipesRemoteDataSource: RecipesRemoteDataSource,
        recipesDataToDomainMapper: RecipesDataToDomainMapper
    ): RecipesRepository = RecipesRepositoryImpl(
        recipesRemoteDataSource = recipesRemoteDataSource,
        recipesDataToDomainMapper,

    )
}