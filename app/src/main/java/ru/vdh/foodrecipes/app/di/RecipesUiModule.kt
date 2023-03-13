package ru.vdh.foodrecipes.app.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesViewState
import ru.vdh.foodrecipes.recipes.ui.binder.RecipesViewStateBinder
import ru.vdh.foodrecipes.recipes.ui.mapper.RecipesNotificationPresentationToUiMapper
import ru.vdh.foodrecipes.recipes.ui.view.RecipesFragment

@Module
@InstallIn(FragmentComponent::class)
class RecipesUiModule {

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesRecipesViewStateBinder(
        fragment: Fragment,
    ) =
        RecipesViewStateBinder(fragment as RecipesFragment, fragment)
                as ViewStateBinder<RecipesViewState, ViewsProvider>

    @Provides
    fun providesRecipesNotificationPresentationToUiMapper() =
        RecipesNotificationPresentationToUiMapper()
}