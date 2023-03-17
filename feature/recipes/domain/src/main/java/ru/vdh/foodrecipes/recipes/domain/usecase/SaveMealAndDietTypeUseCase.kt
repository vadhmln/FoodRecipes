package ru.vdh.foodrecipes.recipes.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.DataStoreRepository

class SaveMealAndDietTypeUseCase(
    private val dataStoreRepository: DataStoreRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<RecipesDomainModel, Flow<RecipesDomainModel>>(coroutineContextProvider) {

    override fun executeInBackground(request: RecipesDomainModel): Flow<RecipesDomainModel> {
        TODO("Not yet implemented")
    }

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)

    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    val readBackOnline = dataStoreRepository.readBackOnline

}