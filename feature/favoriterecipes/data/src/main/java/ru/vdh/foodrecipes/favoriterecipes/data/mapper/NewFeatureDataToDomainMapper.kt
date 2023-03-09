package ru.vdh.foodrecipes.favoriterecipes.data.mapper

import ru.vdh.foodrecipes.favoriterecipes.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel

class NewFeatureDataToDomainMapper {

    fun toDomain(newFeatureDataModel: NewFeatureDataModel) =
        NewFeatureDomainModel(
            firstName = newFeatureDataModel.firstName,
            lastName = newFeatureDataModel.lastName
        )
}