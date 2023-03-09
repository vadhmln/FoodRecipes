package ru.vdh.foodrecipes.newfeature.data.repository

import ru.vdh.foodrecipes.newfeature.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.newfeature.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.recipes.domain.repository.NewFeatureRepository

class NewFeatureRepositoryImpl(
    private val newFeatureDataSource: NewFeatureDataSource,
    private val newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
) : NewFeatureRepository {


}


