package ru.vdh.foodrecipes.recipes.data.repository

import ru.vdh.foodrecipes.recipes.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.recipes.domain.repository.NewFeatureRepository

class NewFeatureRepositoryImpl(
    private val newFeatureDataSource: NewFeatureDataSource,
    private val newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
) : NewFeatureRepository {


}


