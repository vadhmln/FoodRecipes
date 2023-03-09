package ru.vdh.foodrecipes.favoriterecipes.datasource.mapper

import ru.vdh.foodrecipes.favoriterecipes.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.favoriterecipes.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}