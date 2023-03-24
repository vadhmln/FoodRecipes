package ru.vdh.foodrecipes.recipedetails.presentation.mapper

import ru.vdh.foodrecipes.recipedetails.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeaturePresentationModel

class NewFeatureDomainToPresentationMapper {

    fun toPresentation(user: NewFeatureDomainModel) =
        NewFeaturePresentationModel(
            firstName = user.firstName,
            lastName = user.lastName
        )
}