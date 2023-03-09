package ru.vdh.foodrecipes.foodjoke.domain.repository

import ru.vdh.foodrecipes.foodjoke.domain.model.NewFeatureDomainModel

interface NewFeatureRepository {

    fun save(newFeature: NewFeatureDomainModel): Boolean

    fun get(): NewFeatureDomainModel
}