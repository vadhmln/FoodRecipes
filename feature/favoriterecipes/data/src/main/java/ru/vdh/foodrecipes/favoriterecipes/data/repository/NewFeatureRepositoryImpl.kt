package ru.vdh.foodrecipes.favoriterecipes.data.repository

import ru.vdh.foodrecipes.favoriterecipes.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.favoriterecipes.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.favoriterecipes.data.mapper.NewFeatureDataToDomainMapper
import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.NewFeatureRepository

class NewFeatureRepositoryImpl(
    private val newFeatureDataSource: NewFeatureDataSource,
    private val newFeatureDataToDomainMapper: NewFeatureDataToDomainMapper,
    private val newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
) : NewFeatureRepository {

    override fun save(newFeature: NewFeatureDomainModel): Boolean {
        val user = newFeatureDataToDataSourceMapper.toDataSource(newFeature)
        return newFeatureDataSource.save(user)
    }

    override fun get(): NewFeatureDomainModel {
        val user = newFeatureDataSource.get()
        return newFeatureDataToDomainMapper.toDomain(user)
    }
}


