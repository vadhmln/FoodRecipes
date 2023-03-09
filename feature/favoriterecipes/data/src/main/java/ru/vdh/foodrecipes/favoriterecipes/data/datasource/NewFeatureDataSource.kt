package ru.vdh.foodrecipes.favoriterecipes.data.datasource

import ru.vdh.foodrecipes.favoriterecipes.data.model.NewFeatureDataModel

interface NewFeatureDataSource {
    fun save(newFeatureDataModel: NewFeatureDataModel): Boolean

    fun get(): NewFeatureDataModel
}