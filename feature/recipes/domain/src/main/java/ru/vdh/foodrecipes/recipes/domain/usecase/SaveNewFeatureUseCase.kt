package ru.vdh.foodrecipes.recipes.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository

class SaveNewFeatureUseCase(
    private val recipesRepository: RecipesRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<RecipesDomainModel, Unit>(coroutineContextProvider) {

    override fun executeInBackground(request: RecipesDomainModel): Unit = Unit
}



