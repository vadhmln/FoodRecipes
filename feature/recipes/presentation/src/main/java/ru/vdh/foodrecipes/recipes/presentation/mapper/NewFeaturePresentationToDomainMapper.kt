package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
