package ru.vdh.foodrecipes.favoriterecipes.domain.usecase

import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.BackgroundExecutingUseCase
import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.NewFeatureRepository

class SaveNewFeatureUseCase(
    private val newFeatureRepository: NewFeatureRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecutingUseCase<NewFeatureDomainModel, Boolean>(coroutineContextProvider) {

    override fun executeInBackground(request: NewFeatureDomainModel): Boolean {
        //данная проверка проводится в презентейшн слое, здесь для демонстрации
        val oldUserName = newFeatureRepository.get()
        if (oldUserName.firstName == request.firstName) {
            return true
        }
        return newFeatureRepository.save(newFeature = request)
    }
}



