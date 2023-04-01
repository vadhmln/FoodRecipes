package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.foodjoke.data.datasource.FoodJokeDataSource
import ru.vdh.foodrecipes.foodjoke.data.mapper.FoodJokeDataToDomainMapper
import ru.vdh.foodrecipes.foodjoke.data.mapper.FoodJokeTextDomainToDataMapper
import ru.vdh.foodrecipes.foodjoke.data.repository.FoodJokeRepositoryImpl
import ru.vdh.foodrecipes.foodjoke.domain.repository.FoodJokeRepository
import ru.vdh.foodrecipes.foodjoke.presentation.mapper.FoodJokeDomainToPresentationMapper
import ru.vdh.foodrecipes.foodjoke.presentation.mapper.FoodJokeTextDomainToPresentationMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodJokeDataModule {

    @Provides
    fun providesFoodJokeDataToDomainMapper() = FoodJokeDataToDomainMapper()

    @Provides
    fun providesFoodJokeDomainToPresentationMapper() = FoodJokeDomainToPresentationMapper()

    @Provides
    fun providesFoodJokeTextDomainToPresentationMapper() = FoodJokeTextDomainToPresentationMapper()

    @Provides
    fun providesFoodJokeTextDomainToDataMapper() = FoodJokeTextDomainToDataMapper()

    @Provides
    @Singleton
    fun provideFoodJokeRepository(
        foodJokeDataSource: FoodJokeDataSource,
        foodJokeDataToDomainMapper: FoodJokeDataToDomainMapper,
        foodJokeTextDomainToDataMapper: FoodJokeTextDomainToDataMapper,
    ): FoodJokeRepository = FoodJokeRepositoryImpl(
        foodJokeDataSource,
        foodJokeDataToDomainMapper,
        foodJokeTextDomainToDataMapper
    )
}