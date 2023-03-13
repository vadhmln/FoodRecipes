package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository
import ru.vdh.foodrecipes.recipes.domain.usecase.GetRecipesUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveNewFeatureUseCase

@Module
@InstallIn(ViewModelComponent::class)
class RecipesDomainModule {

    @Provides
    fun provideGetNewFeatureUseCase(
        recipesRepository: RecipesRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetRecipesUseCase =
        GetRecipesUseCase(
            recipesRepository = recipesRepository,
            coroutineContextProvider = coroutineContextProvider
        )


    @Provides
    fun provideSaveNewFeatureUseCase(
        recipesRepository: RecipesRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SaveNewFeatureUseCase =
        SaveNewFeatureUseCase(
            recipesRepository = recipesRepository,
            coroutineContextProvider = coroutineContextProvider
        )

}