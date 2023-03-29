package ru.vdh.foodrecipes.favoriterecipes.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.FavoriteRecipeRepository

class GetFavoriteRecipesUseCase(
    private val favoriteRecipeRepository: FavoriteRecipeRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, Unit>(
    coroutineContextProvider
) {

    override fun executeInBackground(request: String) = Unit

    fun getFavoriteRecipes() =
        favoriteRecipeRepository.getFavoriteRecipes()
}