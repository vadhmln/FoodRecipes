package ru.vdh.foodrecipes.recipedetails.datasource.mapper

import ru.vdh.foodrecipes.recipedetails.data.model.NewFeatureDataModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(input: NewFeatureDataModel) =
        NewFeatureDataModel(firstName = input.firstName, lastName = "")
}