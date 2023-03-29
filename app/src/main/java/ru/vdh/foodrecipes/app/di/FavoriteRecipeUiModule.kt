package ru.vdh.foodrecipes.app.di

import android.content.Context
import android.view.View
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesViewState
import ru.vdh.foodrecipes.favoriterecipes.ui.binder.NewFeatureViewStateBinder
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.RecipeDetailsNotificationPresentationToUiMapper

@Module
@InstallIn(FragmentComponent::class)
class FavoriteRecipeUiModule {

    @Provides
    fun providesView(@ApplicationContext context: Context) = View(context)

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesUserDetailsViewStateBinder() = NewFeatureViewStateBinder()
            as ViewStateBinder<FavoriteRecipesViewState, ViewsProvider>

    @Provides
    fun providesNewUserNotificationPresentationToUiMapper() =
        RecipeDetailsNotificationPresentationToUiMapper()
}