package ru.vdh.foodrecipes.favoriterecipes.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.FavoriteRecipeRepository

class DeleteAllFavoriteRecipesUseCase(
    private val favoriteRecipeRepository: FavoriteRecipeRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, Unit>(
    coroutineContextProvider
) {

    override fun executeInBackground(request: String) = Unit

    suspend fun deleteAllFavoriteRecipes() {
        favoriteRecipeRepository.deleteAllFavoriteRecipes()
    }
}