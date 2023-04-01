package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.foodjoke.data.datasource.FoodJokeDataSource
import ru.vdh.foodrecipes.foodjoke.datasource.FoodJokeDataSourceImpl
import ru.vdh.foodrecipes.foodjoke.datasource.mapper.FoodJokeDataSourceToDataMapper
import ru.vdh.foodrecipes.foodjoke.datasource.mapper.FoodJokeDataToDataSourceMapper
import ru.vdh.foodrecipes.foodjoke.datasource.mapper.FoodJokeTextDataSourceToDataMapper
import ru.vdh.foodrecipes.network.FoodRecipesApi

@Module
@InstallIn(SingletonComponent::class)
class FoodJokeDataSourceModule {

    @Provides
    fun providesFoodJokeDataSourceToDataMapper() = FoodJokeDataSourceToDataMapper()

    @Provides
    fun providesFoodJokeTextDataSourceToDataMapper() = FoodJokeTextDataSourceToDataMapper()

    @Provides
    fun providesFoodJokeDataToDataSourceMapper() = FoodJokeDataToDataSourceMapper()

    @Provides
    @Singleton
    fun provideFoodJokeDataSource(
        foodRecipesApi: FoodRecipesApi,
        recipesDao: RecipesDao,
        foodJokeDataSourceToDataMapper: FoodJokeDataSourceToDataMapper,
        foodJokeTextDataSourceToDataMapper: FoodJokeTextDataSourceToDataMapper,
        foodJokeDataToDataSourceMapper: FoodJokeDataToDataSourceMapper,
        ): FoodJokeDataSource = FoodJokeDataSourceImpl(
        foodRecipesApi,
        recipesDao,
        foodJokeDataSourceToDataMapper,
        foodJokeTextDataSourceToDataMapper,
        foodJokeDataToDataSourceMapper
    )
}