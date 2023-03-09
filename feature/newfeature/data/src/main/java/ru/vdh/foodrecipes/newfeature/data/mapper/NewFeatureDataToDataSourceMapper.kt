package ru.vdh.foodrecipes.newfeature.data.mapper

import ru.vdh.foodrecipes.newfeature.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.recipes.domain.model.NewFeatureDomainModel

class NewFeatureDataToDataSourceMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}