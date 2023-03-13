package ru.vdh.foodrecipes.newfeature.data.repository

import ru.vdh.foodrecipes.newfeature.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.newfeature.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.newfeature.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.newfeature.domain.repository.NewFeatureRepository

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


