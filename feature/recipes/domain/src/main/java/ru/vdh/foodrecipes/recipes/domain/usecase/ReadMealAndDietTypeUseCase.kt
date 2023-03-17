package ru.vdh.foodrecipes.recipes.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.RecipesRemoteRepository

class ReadMealAndDietTypeUseCase (
    private val recipesRemoteRepository: RecipesRemoteRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<RecipesDomainModel, Flow<RecipesDomainModel>>(coroutineContextProvider) {

    override fun executeInBackground(request: RecipesDomainModel): Flow<RecipesDomainModel> {
        TODO("Not yet implemented")
    }

    suspend fun getRecipes(
        queries: Map<String, String>,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) =
        recipesRemoteRepository.getRecipes(queries, onStart, onComplete, onError)
}