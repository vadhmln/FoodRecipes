package ru.vdh.foodrecipes.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipedetails.ui.binder.NewFeatureViewStateBinder
import ru.vdh.foodrecipes.recipedetails.ui.mapper.NewUserNotificationPresentationToUiMapper

@Module
@InstallIn(FragmentComponent::class)
class RecipeDetailsUiModule {

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesUserDetailsViewStateBinder() = NewFeatureViewStateBinder()
            as ViewStateBinder<NewFeatureViewState, ViewsProvider>

    @Provides
    fun providesNewUserNotificationPresentationToUiMapper() =
        NewUserNotificationPresentationToUiMapper()
}