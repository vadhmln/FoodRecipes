package ru.vdh.foodrecipes.newfeature.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.newfeature.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
