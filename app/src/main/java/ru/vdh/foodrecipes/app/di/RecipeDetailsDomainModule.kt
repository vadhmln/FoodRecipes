package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository
import ru.vdh.foodrecipes.recipedetails.domain.usecase.GetRecipeItemByIdUseCase

@Module
@InstallIn(ViewModelComponent::class)
class RecipeDetailsDomainModule {

    @Provides
    fun provideGetRecipeItemByIdUseCase(
        updateToDoRepository: RecipeDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetRecipeItemByIdUseCase =
        GetRecipeItemByIdUseCase(
            updateToDoRepository = updateToDoRepository,
            coroutineContextProvider = coroutineContextProvider
        )
}