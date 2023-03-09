package ru.vdh.foodrecipes.foodjoke.datasource.mapper

import ru.vdh.foodrecipes.foodjoke.data.model.NewFeatureDataModel


class NewFeatureDataSourceToDataMapper {

    fun toDataSource(input: NewFeatureDataModel) =
        NewFeatureDataModel(firstName = input.firstName, lastName = "")
}