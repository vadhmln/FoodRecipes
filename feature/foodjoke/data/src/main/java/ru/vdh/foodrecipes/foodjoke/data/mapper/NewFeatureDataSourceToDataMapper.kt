package ru.vdh.foodrecipes.foodjoke.data.mapper

import ru.vdh.foodrecipes.foodjoke.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel


class NewFeatureDataSourceToDataMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}