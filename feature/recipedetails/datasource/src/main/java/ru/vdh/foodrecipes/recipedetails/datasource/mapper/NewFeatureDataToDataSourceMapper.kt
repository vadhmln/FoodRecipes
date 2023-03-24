package ru.vdh.foodrecipes.recipedetails.datasource.mapper

import ru.vdh.foodrecipes.recipedetails.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.recipedetails.datasource.model.NewFeatureDataSourceModel

class NewFeatureDataToDataSourceMapper {

    fun toData(userName: NewFeatureDataSourceModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}