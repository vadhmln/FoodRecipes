package ru.vdh.foodrecipes.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.recipes.data.datasource.NewFeatureDataSource
import ru.vdh.foodrecipes.recipes.data.mapper.NewFeatureDataToDataSourceMapper
import ru.vdh.foodrecipes.recipes.data.repository.NewFeatureRepositoryImpl
import ru.vdh.foodrecipes.recipes.datasource.SharedPrefNewFeatureDataSource
import ru.vdh.foodrecipes.recipes.domain.repository.NewFeatureRepository
import ru.vdh.foodrecipes.recipes.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.NewFeaturePresentationToDomainMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewFeatureDataModule {

    @Provides
    fun providesNewFeatureDataModelToDataSourceMapper() = NewFeatureDataToDataSourceMapper()

    @Provides
    fun providesNewFeaturePresentationToDomainMapper() = NewFeaturePresentationToDomainMapper()

    @Provides
    fun providesNewFeatureDomainToPresentationMapper() = NewFeatureDomainToPresentationMapper()

    @Provides
    @Singleton
    fun provideNewFeatureDataSource(@ApplicationContext context: Context): NewFeatureDataSource {
        return SharedPrefNewFeatureDataSource(context = context)
    }

    @Provides
    @Singleton
    fun provideNewFeatureRepository(
        newFeatureDataSource: NewFeatureDataSource,
        newFeatureDataToDataSourceMapper: NewFeatureDataToDataSourceMapper
    ): NewFeatureRepository = NewFeatureRepositoryImpl(
        newFeatureDataSource = newFeatureDataSource,
        newFeatureDataToDataSourceMapper,

    )
}