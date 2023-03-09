package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.recipes.domain.repository.NewFeatureRepository
import ru.vdh.foodrecipes.recipes.domain.usecase.GetNewFeatureUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveNewFeatureUseCase

@Module
@InstallIn(ViewModelComponent::class)
class NewFeatureDomainModule {

    @Provides
    fun provideGetNewFeatureUseCase(
        newFeatureRepository: NewFeatureRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetNewFeatureUseCase =
        GetNewFeatureUseCase(
            newFeatureRepository = newFeatureRepository,
            coroutineContextProvider = coroutineContextProvider
        )


    @Provides
    fun provideSaveNewFeatureUseCase(
        newFeatureRepository: NewFeatureRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): SaveNewFeatureUseCase =
        SaveNewFeatureUseCase(
            newFeatureRepository = newFeatureRepository,
            coroutineContextProvider = coroutineContextProvider
        )

}