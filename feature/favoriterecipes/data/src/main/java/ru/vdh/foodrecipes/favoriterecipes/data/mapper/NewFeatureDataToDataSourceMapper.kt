package ru.vdh.foodrecipes.favoriterecipes.data.mapper

import ru.vdh.foodrecipes.favoriterecipes.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel

class NewFeatureDataToDataSourceMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}