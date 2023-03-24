package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.recipedetails.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.data.repository.RecipeDetailsRepositoryImpl
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.NewFeaturePresentationToDomainMapper
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.RecipesDomainToPresentationMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDetailsDataModule {

    @Provides
    fun providesNewFeatureDataModelToDataSourceMapper() = NewFeatureDataToDataSourceMapper()

    @Provides
    fun providesNewFeaturePresentationToDomainMapper() = NewFeaturePresentationToDomainMapper()

    @Provides
    fun providesNewFeatureDomainToPresentationMapper() = NewFeatureDomainToPresentationMapper()

    @Provides
    fun providesRecipesDataToDomainMapper() = RecipesDataToDomainMapper()

    @Provides
    fun providesRecipesDomainToPresentationMapper() = RecipesDomainToPresentationMapper()

    @Provides
    @Singleton
    fun provideRecipeDetailsRepository(
        recipeDetailsDataSource: RecipeDetailsDataSource,
        recipesDataToDomainMapper: RecipesDataToDomainMapper
    ): RecipeDetailsRepository = RecipeDetailsRepositoryImpl(
        recipeDetailsDataSource = recipeDetailsDataSource,
        recipesDataToDomainMapper
    )
}