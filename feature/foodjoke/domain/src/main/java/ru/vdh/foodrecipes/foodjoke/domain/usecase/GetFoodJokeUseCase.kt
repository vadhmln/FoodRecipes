package ru.vdh.foodrecipes.foodjoke.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.repository.FoodJokeRepository

class GetFoodJokeUseCase(
    private val foodJokeRepository: FoodJokeRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, Unit>(
    coroutineContextProvider
) {

    override fun executeInBackground(request: NewFeatureDomainModel) = Unit

    fun readFoodJoke(): Flow<List<FoodJokeDomainModel>> =
        foodJokeRepository.readFoodJoke()

}