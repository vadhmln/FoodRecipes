package ru.vdh.foodrecipes.favoriterecipes.datasource.mapper

import ru.vdh.foodrecipes.favoriterecipes.data.model.NewFeatureDataModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(input: NewFeatureDataModel) =
        NewFeatureDataModel(firstName = input.firstName, lastName = "")
}