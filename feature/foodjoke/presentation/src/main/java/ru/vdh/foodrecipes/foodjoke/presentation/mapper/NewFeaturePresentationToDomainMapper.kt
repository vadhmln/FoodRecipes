package ru.vdh.foodrecipes.foodjoke.presentation.mapper

import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
