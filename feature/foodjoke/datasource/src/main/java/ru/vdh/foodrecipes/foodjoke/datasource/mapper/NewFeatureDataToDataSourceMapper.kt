package ru.vdh.foodrecipes.foodjoke.datasource.mapper

import ru.vdh.foodrecipes.foodjoke.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.foodjoke.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}