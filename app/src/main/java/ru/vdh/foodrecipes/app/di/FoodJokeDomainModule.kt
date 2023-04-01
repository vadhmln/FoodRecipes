package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.foodjoke.domain.repository.FoodJokeRepository
import ru.vdh.foodrecipes.foodjoke.domain.usecase.GetFoodJokeFromRemoteDataSourceUseCase
import ru.vdh.foodrecipes.foodjoke.domain.usecase.GetFoodJokeUseCase

@Module
@InstallIn(ViewModelComponent::class)
class FoodJokeDomainModule {

    @Provides
    fun provideGetFoodJokeFromRemoteDataSourceUseCase(
        foodJokeRepository: FoodJokeRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetFoodJokeFromRemoteDataSourceUseCase =
        GetFoodJokeFromRemoteDataSourceUseCase(
            foodJokeRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideGetFoodJokeUseCase(
        foodJokeRepository: FoodJokeRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetFoodJokeUseCase =
        GetFoodJokeUseCase(
            foodJokeRepository,
            coroutineContextProvider = coroutineContextProvider
        )
}