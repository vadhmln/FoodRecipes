package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vdh.foodrecipes.core.domain.coroutine.CoroutineContextProvider
import ru.vdh.foodrecipes.core.domain.usecase.UseCaseExecutor
import ru.vdh.foodrecipes.core.presentation.viewmodel.coroutine.AppCoroutineContextProvider
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider

@Module
@InstallIn(SingletonComponent::class)
class CorePresentationModule {
    @Provides
    fun providesCoroutineContextProvider(): CoroutineContextProvider =
        AppCoroutineContextProvider()

    @Provides
    fun providesUseCaseExecutorProvider(): UseCaseExecutorProvider = ::UseCaseExecutor
}
