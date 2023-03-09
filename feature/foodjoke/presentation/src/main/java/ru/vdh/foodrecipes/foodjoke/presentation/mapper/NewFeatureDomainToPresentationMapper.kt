package ru.vdh.foodrecipes.foodjoke.presentation.mapper

import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}