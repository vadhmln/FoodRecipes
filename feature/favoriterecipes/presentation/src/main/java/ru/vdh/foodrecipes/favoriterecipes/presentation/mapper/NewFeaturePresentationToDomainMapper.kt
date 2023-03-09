package ru.vdh.foodrecipes.favoriterecipes.presentation.mapper

import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
