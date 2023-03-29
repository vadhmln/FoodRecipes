package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.favoriterecipes.data.datasource.FavoriteRecipeDataSource
import ru.vdh.foodrecipes.favoriterecipes.datasource.FavoriteRecipeDataSourceImpl
import ru.vdh.foodrecipes.favoriterecipes.datasource.mapper.FavoriteRecipeDataToDatabaseMapper
import ru.vdh.foodrecipes.favoriterecipes.datasource.mapper.FavoriteRecipeDatabaseToDataMapper

@Module
@InstallIn(SingletonComponent::class)
class FavoriteRecipeDataSourceModule {

    @Provides
    fun providesFavoriteRecipeDataToDatabaseMapper() = FavoriteRecipeDataToDatabaseMapper()

    @Provides
    fun providesFavoriteRecipeDatabaseToDataMapper() = FavoriteRecipeDatabaseToDataMapper()

    @Provides
    @Singleton
    fun provideFavoriteRecipeDataSource(
        recipesDao: RecipesDao,
        favoriteRecipeDataToDatabaseMapper: FavoriteRecipeDataToDatabaseMapper,
        favoriteRecipeDatabaseToDataMapper: FavoriteRecipeDatabaseToDataMapper,
    ): FavoriteRecipeDataSource = FavoriteRecipeDataSourceImpl(
        recipesDao,
        favoriteRecipeDataToDatabaseMapper,
        favoriteRecipeDatabaseToDataMapper,
    )
}