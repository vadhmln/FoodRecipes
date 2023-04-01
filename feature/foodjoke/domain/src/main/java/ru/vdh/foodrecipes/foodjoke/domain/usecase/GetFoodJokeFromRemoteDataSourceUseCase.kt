package ru.vdh.foodrecipes.foodjoke.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.foodjoke.domain.model.FoodJokeTextDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.repository.FoodJokeRepository

class GetFoodJokeFromRemoteDataSourceUseCase (
    private val foodJokeRepository: FoodJokeRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, Unit>(
    coroutineContextProvider
) {

    override fun executeInBackground(request: NewFeatureDomainModel) = Unit

    suspend fun getFoodJoke(apiKey: String): Flow<FoodJokeTextDomainModel> =
        foodJokeRepository.getFoodJoke(apiKey)

}
