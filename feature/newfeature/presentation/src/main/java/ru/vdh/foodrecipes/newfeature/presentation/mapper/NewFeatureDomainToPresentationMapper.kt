package ru.vdh.foodrecipes.newfeature.presentation.mapper

import ru.vdh.foodrecipes.newfeature.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.newfeature.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}