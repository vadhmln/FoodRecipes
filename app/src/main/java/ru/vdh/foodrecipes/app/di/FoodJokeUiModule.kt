package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.foodjoke.ui.binder.FoodJokeViewStateBinder
import ru.vdh.foodrecipes.foodjoke.ui.mapper.FoodJokeNotificationPresentationToUiMapper


@Module
@InstallIn(FragmentComponent::class)
class FoodJokeUiModule {

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesUserDetailsViewStateBinder() = FoodJokeViewStateBinder()
            as ViewStateBinder<NewFeatureViewState, ViewsProvider>

    @Provides
    fun providesNewUserNotificationPresentationToUiMapper() =
        FoodJokeNotificationPresentationToUiMapper()
}