package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}