package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.recipes.domain.repository.DataStoreRepository
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRemoteRepository
import ru.vdh.foodrecipes.recipes.domain.usecase.GetRecipesUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveMealAndDietTypeUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveNewFeatureUseCase

@Module
@InstallIn(ViewModelComponent::class)
class RecipesDomainModule {

    @Provides
    fun provideGetRecipesUseCase(
        recipesRemoteRepository: RecipesRemoteRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetRecipesUseCase =
        GetRecipesUseCase(
            recipesRemoteRepository = recipesRemoteRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideSaveNewFeatureUseCase(
        dataStoreRepository: DataStoreRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SaveMealAndDietTypeUseCase =
        SaveMealAndDietTypeUseCase(
            dataStoreRepository,
            coroutineContextProvider = coroutineContextProvider
        )

}