package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.recipes.domain.repository.DataStoreRepository
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository
import ru.vdh.foodrecipes.recipes.domain.usecase.GetRecipesUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.ReadDatabaseUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveMealAndDietTypeUseCase

@Module
@InstallIn(ViewModelComponent::class)
class RecipesDomainModule {

    @Provides
    fun provideGetRecipesUseCase(
        recipesRepository: RecipesRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetRecipesUseCase =
        GetRecipesUseCase(
            recipesRepository = recipesRepository,
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

    @Provides
    fun provideReadDatabaseUseCase(
        recipesRepository: RecipesRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): ReadDatabaseUseCase =
        ReadDatabaseUseCase(
            recipesRepository,
            coroutineContextProvider = coroutineContextProvider
        )

}