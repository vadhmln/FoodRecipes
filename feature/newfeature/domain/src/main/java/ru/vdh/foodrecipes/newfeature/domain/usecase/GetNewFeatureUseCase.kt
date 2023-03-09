package ru.vdh.foodrecipes.newfeature.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.newfeature.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.newfeature.domain.repository.NewFeatureRepository

class GetNewFeatureUseCase(
    private val newFeatureRepository: NewFeatureRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, NewFeatureDomainModel>(coroutineContextProvider) {

    fun execute(): NewFeatureDomainModel {
        return newFeatureRepository.get()
    }

    override fun executeInBackground(request: NewFeatureDomainModel) = newFeatureRepository.get()
}