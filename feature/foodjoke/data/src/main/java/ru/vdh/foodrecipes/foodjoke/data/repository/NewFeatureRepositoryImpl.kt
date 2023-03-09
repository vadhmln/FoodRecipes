package ru.vdh.foodrecipes.foodjoke.data.repository

import ru.vdh.foodrecipes.foodjoke.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.foodjoke.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.foodjoke.domain.repository.NewFeatureRepository


class NewFeatureRepositoryImpl(
    private val newFeatureDataSource: NewFeatureDataSource,
    private val newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
) : NewFeatureRepository {
    override fun save(newFeature: NewFeatureDomainModel): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(): NewFeatureDomainModel {
        TODO("Not yet implemented")
    }


}


