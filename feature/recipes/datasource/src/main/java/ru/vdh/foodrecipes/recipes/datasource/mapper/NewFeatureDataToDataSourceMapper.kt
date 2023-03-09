package ru.vdh.foodrecipes.recipes.datasource.mapper

import ru.vdh.foodrecipes.recipes.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.recipes.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}