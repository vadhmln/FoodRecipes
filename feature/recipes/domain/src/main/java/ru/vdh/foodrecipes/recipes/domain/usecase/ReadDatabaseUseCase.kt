package ru.vdh.foodrecipes.recipes.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRepository

class ReadDatabaseUseCase (
    private val recipesRepository: RecipesRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<Int, Flow<List<RecipesDomainModel>>>(coroutineContextProvider) {

    override fun executeInBackground(request: Int) =
        recipesRepository.readDatabase()

}