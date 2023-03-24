package ru.vdh.foodrecipes.recipedetails.data.mapper

import ru.vdh.foodrecipes.recipedetails.data.model.NewFeatureDataModel
import ru.vdh.foodrecipes.recipedetails.domain.model.NewFeatureDomainModel

class NewFeatureDataSourceToDataMapper {

    fun toDataSource(userName: NewFeatureDomainModel) =
        NewFeatureDataModel(firstName = userName.firstName, lastName = "")
}