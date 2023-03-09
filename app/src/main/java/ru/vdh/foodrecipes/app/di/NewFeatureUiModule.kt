package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipes.ui.binder.RecipesViewStateBinder
import ru.vdh.foodrecipes.recipes.ui.mapper.RecipesNotificationPresentationToUiMapper

@Module
@InstallIn(FragmentComponent::class)
class NewFeatureUiModule {

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesUserDetailsViewStateBinder() = RecipesViewStateBinder()
            as ViewStateBinder<NewFeatureViewState, ViewsProvider>

    @Provides
    fun providesNewUserNotificationPresentationToUiMapper() =
        RecipesNotificationPresentationToUiMapper()
}