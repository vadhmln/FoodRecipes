package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.vdh.foodrecipes.favoriterecipes.data.datasource.FavoriteRecipeDataSource
import ru.vdh.foodrecipes.favoriterecipes.data.mapper.FavoriteRecipeDataToDomainMapper
import ru.vdh.foodrecipes.favoriterecipes.data.mapper.FavoriteRecipeDomainToDataMapper
import ru.vdh.foodrecipes.favoriterecipes.data.repository.FavoriteRecipeRepositoryImpl
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.FavoriteRecipeRepository
import ru.vdh.foodrecipes.favoriterecipes.presentation.mapper.FavoriteRecipeDomainToPresentationMapper
import ru.vdh.foodrecipes.favoriterecipes.presentation.mapper.FavoriteRecipePresentationToDomainMapper

@Module
@InstallIn(SingletonComponent::class)
class FavoriteRecipeDataModule {

    @Provides
    fun providesFavoriteRecipeDataToDomainMapper() = FavoriteRecipeDataToDomainMapper()

    @Provides
    fun providesFavoriteRecipeDomainToDataMapper() = FavoriteRecipeDomainToDataMapper()

    @Provides
    fun providesFavoriteRecipePresentationToDomainMapper() = FavoriteRecipePresentationToDomainMapper()

    @Provides
    fun providesFavoriteRecipeDomainToPresentationMapper() = FavoriteRecipeDomainToPresentationMapper()

    @Provides
    @Singleton
    fun provideFavoriteRecipeRepository(
        favoriteRecipeDataSource: FavoriteRecipeDataSource,
        favoriteRecipeDataToDomainMapper: FavoriteRecipeDataToDomainMapper,
        favoriteRecipeDomainToDataMapper: FavoriteRecipeDomainToDataMapper,
    ): FavoriteRecipeRepository = FavoriteRecipeRepositoryImpl(
        favoriteRecipeDataSource,
        favoriteRecipeDataToDomainMapper,
        favoriteRecipeDomainToDataMapper
    )
}