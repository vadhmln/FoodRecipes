package ru.vdh.foodrecipes.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.foodjoke.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.foodjoke.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.foodjoke.datasource.SharedPrefNewFeatureDataSource
import ru.vdh.foodrecipes.foodjoke.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.foodrecipes.foodjoke.presentation.mapper.NewFeaturePresentationToDomainMapper


import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodJokeDataModule {


    @Provides
    fun providesNewFeatureDataModelToDataSourceMapper() = NewFeatureDataToDataSourceMapper()

    @Provides
    fun providesNewFeaturePresentationToDomainMapper() = NewFeaturePresentationToDomainMapper()

    @Provides
    fun providesNewFeatureDomainToPresentationMapper() = NewFeatureDomainToPresentationMapper()




}