package ru.vdh.foodrecipes.newfeature.datasource.mapper

import ru.vdh.foodrecipes.recipes.data.model.NewFeatureDataModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(input: NewFeatureDataModel) =
        NewFeatureDataModel(firstName = input.firstName, lastName = "")
}