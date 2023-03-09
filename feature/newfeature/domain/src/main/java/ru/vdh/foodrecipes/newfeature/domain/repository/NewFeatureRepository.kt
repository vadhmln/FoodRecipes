package ru.vdh.foodrecipes.newfeature.domain.repository

import ru.vdh.foodrecipes.newfeature.domain.model.NewFeatureDomainModel

interface NewFeatureRepository {

    fun save(newFeature: NewFeatureDomainModel): Boolean

    fun get(): NewFeatureDomainModel
}