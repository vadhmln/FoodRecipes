package ru.vdh.foodrecipes.favoriterecipes.presentation.mapper

import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}