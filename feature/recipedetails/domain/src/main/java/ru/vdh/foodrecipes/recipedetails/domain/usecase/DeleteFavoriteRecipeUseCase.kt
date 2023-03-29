package ru.vdh.foodrecipes.recipedetails.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipedetails.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository

class DeleteFavoriteRecipeUseCase(
    private val recipeDetailsRepository: RecipeDetailsRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<FavoritesDomainModel, Unit>(
    coroutineContextProvider
) {

    override fun executeInBackground(request: FavoritesDomainModel) = Unit

    suspend fun delete(request: FavoritesDomainModel) =
        recipeDetailsRepository.deleteFavoriteRecipe(request)
}