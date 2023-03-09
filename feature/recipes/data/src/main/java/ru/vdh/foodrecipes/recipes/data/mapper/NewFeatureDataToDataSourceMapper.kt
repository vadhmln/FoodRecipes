package ru.vdh.foodrecipes.recipes.data.mapper

import ru.vdh.foodrecipes.recipes.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.recipes.domain.model.NewFeatureDomainModel

class NewFeatureDataToDataSourceMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}