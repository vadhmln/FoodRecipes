package ru.vdh.foodrecipes.foodjoke.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.repository.FoodJokeRepository

class SaveNewFeatureUseCase(
    private val foodJokeRepository: FoodJokeRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, Unit>(coroutineContextProvider) {

    override fun executeInBackground(request: NewFeatureDomainModel) = Unit
}



