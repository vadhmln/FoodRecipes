package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.recipes.data.datasource.RecipesRemoteDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.ErrorResponseToDomainMapper
import ru.vdh.foodrecipes.recipes.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipes.data.repository.RecipesRepositoryImpl
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository
import ru.vdh.foodrecipes.recipes.presentation.mapper.ErrorResponseDomainToPresentationMapper
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
    fun providesErrorResponseDomainToPresentationMapper() = ErrorResponseDomainToPresentationMapper()

    @Provides
    fun providesErrorResponseToDomainMapper() = ErrorResponseToDomainMapper()

    @Provides
    @Singleton
    fun provideNewFeatureRepository(
        recipesRemoteDataSource: RecipesRemoteDataSource,
        recipesDataToDomainMapper: RecipesDataToDomainMapper,
        errorResponseToDomainMapper: ErrorResponseToDomainMapper,
    ): RecipesRepository = RecipesRepositoryImpl(
        recipesRemoteDataSource = recipesRemoteDataSource,
        recipesDataToDomainMapper,
        errorResponseToDomainMapper
    )
}