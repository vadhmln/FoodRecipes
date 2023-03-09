package ru.vdh.foodrecipes.recipes.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.recipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.recipes.domain.repository.NewFeatureRepository

class SaveNewFeatureUseCase(
    private val newFeatureRepository: NewFeatureRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, Unit>(coroutineContextProvider) {

    override fun executeInBackground(request: NewFeatureDomainModel): Unit = Unit
}



