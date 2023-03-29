package ru.vdh.foodrecipes.recipedetails.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipedetails.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.ResultDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository

class GetFavoriteRecipesUseCase(
    private val recipeDetailsRepository: RecipeDetailsRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<String, Unit>(
    coroutineContextProvider
) {

    override fun executeInBackground(request: String) = Unit

    fun getFavoriteRecipes() =
        recipeDetailsRepository.getFavoriteRecipes()
}