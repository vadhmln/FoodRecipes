package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.mapper.FavoriteRecipeDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.data.mapper.FavoriteRecipeDomainToDataMapper
import ru.vdh.foodrecipes.recipedetails.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.data.repository.RecipeDetailsRepositoryImpl
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.FavoriteRecipeDomainToPresentationMapper
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.FavoriteRecipePresentationToDomainMapper
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.RecipesDomainToPresentationMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDetailsDataModule {

    @Provides
    fun providesRecipesDataToDomainMapper() = RecipesDataToDomainMapper()

    @Provides
    fun providesRecipesDomainToPresentationMapper() = RecipesDomainToPresentationMapper()

    @Provides
    fun providesFavoriteRecipePresentationToDomainMapper() = FavoriteRecipePresentationToDomainMapper()

    @Provides
    fun providesFavoriteRecipeDomainToDataMapper() = FavoriteRecipeDomainToDataMapper()

    @Provides
    fun providesFavoriteRecipeDataToDomainMapper() = FavoriteRecipeDataToDomainMapper()

    @Provides
    fun providesFavoriteRecipeDomainToPresentationMapper() = FavoriteRecipeDomainToPresentationMapper()

    @Provides
    @Singleton
    fun provideRecipeDetailsRepository(
        recipeDetailsDataSource: RecipeDetailsDataSource,
        recipesDataToDomainMapper: RecipesDataToDomainMapper,
        favoriteRecipeDomainToDataMapper: FavoriteRecipeDomainToDataMapper,
        favoriteRecipeDataToDomainMapper: FavoriteRecipeDataToDomainMapper,
    ): RecipeDetailsRepository = RecipeDetailsRepositoryImpl(
        recipeDetailsDataSource = recipeDetailsDataSource,
        recipesDataToDomainMapper,
        favoriteRecipeDomainToDataMapper,
        favoriteRecipeDataToDomainMapper,
    )
}