package ru.vdh.foodrecipes.recipedetails.presentation.mapper

import ru.vdh.foodrecipes.recipedetails.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeaturePresentationModel

class NewFeaturePresentationToDomainMapper {

    fun toDomain(newUser: NewFeaturePresentationModel) =
        NewFeatureDomainModel(
            firstName = newUser.firstName,
            lastName = newUser.lastName
        )
}
