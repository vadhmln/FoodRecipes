package ru.vdh.foodrecipes.newfeature.data.mapper

import ru.vdh.foodrecipes.newfeature.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.newfeature.domain.model.NewFeatureDomainModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}