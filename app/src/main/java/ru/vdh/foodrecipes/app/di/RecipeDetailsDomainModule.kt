package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository
import ru.vdh.foodrecipes.recipedetails.domain.usecase.DeleteFavoriteRecipeUseCase
import ru.vdh.foodrecipes.recipedetails.domain.usecase.GetFavoriteRecipesUseCase
import ru.vdh.foodrecipes.recipedetails.domain.usecase.GetRecipeItemByIdUseCase
import ru.vdh.foodrecipes.recipedetails.domain.usecase.InsertFavoriteRecipeUseCase

@Module
@InstallIn(ViewModelComponent::class)
class RecipeDetailsDomainModule {

    @Provides
    fun provideGetRecipeItemByIdUseCase(
        recipeDetailsRepository: RecipeDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetRecipeItemByIdUseCase =
        GetRecipeItemByIdUseCase(
            recipeDetailsRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideInsertFavoriteRecipeItemUseCase(
        recipeDetailsRepository: RecipeDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): InsertFavoriteRecipeUseCase =
        InsertFavoriteRecipeUseCase(
            recipeDetailsRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideDeleteFavoriteRecipeUseCase(
        recipeDetailsRepository: RecipeDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): DeleteFavoriteRecipeUseCase =
        DeleteFavoriteRecipeUseCase(
            recipeDetailsRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideGetFavoriteRecipesUseCase(
        recipeDetailsRepository: RecipeDetailsRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetFavoriteRecipesUseCase =
        GetFavoriteRecipesUseCase(
            recipeDetailsRepository,
            coroutineContextProvider = coroutineContextProvider
        )
}