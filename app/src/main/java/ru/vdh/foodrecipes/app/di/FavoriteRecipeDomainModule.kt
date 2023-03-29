package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.FavoriteRecipeRepository
import ru.vdh.foodrecipes.favoriterecipes.domain.usecase.DeleteAllFavoriteRecipesUseCase
import ru.vdh.foodrecipes.favoriterecipes.domain.usecase.DeleteFavoriteRecipeUseCase
import ru.vdh.foodrecipes.favoriterecipes.domain.usecase.GetFavoriteRecipesUseCase

@Module
@InstallIn(ViewModelComponent::class)
class FavoriteRecipeDomainModule {

    @Provides
    fun provideGetFavoriteRecipesUseCase(
        favoriteRecipeRepository: FavoriteRecipeRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetFavoriteRecipesUseCase =
        GetFavoriteRecipesUseCase(
            favoriteRecipeRepository = favoriteRecipeRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideDeleteFavoriteRecipesUseCase(
        favoriteRecipeRepository: FavoriteRecipeRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): DeleteFavoriteRecipeUseCase =
        DeleteFavoriteRecipeUseCase(
            favoriteRecipeRepository = favoriteRecipeRepository,
            coroutineContextProvider = coroutineContextProvider
        )

    @Provides
    fun provideDeleteAllFavoriteRecipesUseCase(
        favoriteRecipeRepository: FavoriteRecipeRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): DeleteAllFavoriteRecipesUseCase =
        DeleteAllFavoriteRecipesUseCase(
            favoriteRecipeRepository = favoriteRecipeRepository,
            coroutineContextProvider = coroutineContextProvider
        )
}