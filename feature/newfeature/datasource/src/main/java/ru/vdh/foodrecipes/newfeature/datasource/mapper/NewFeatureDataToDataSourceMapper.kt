package ru.vdh.foodrecipes.newfeature.datasource.mapper

import ru.vdh.foodrecipes.newfeature.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.newfeature.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}