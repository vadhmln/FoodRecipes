package ru.vdh.foodrecipes.favoriterecipes.domain.repository

import ru.vdh.foodrecipes.favoriterecipes.domain.model.NewFeatureDomainModel

interface NewFeatureRepository {

    fun save(newFeature: NewFeatureDomainModel): Boolean

    fun get(): NewFeatureDomainModel
}