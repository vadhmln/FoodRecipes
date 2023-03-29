package ru.vdh.foodrecipes.app.di

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesViewState
import ru.vdh.foodrecipes.favoriterecipes.ui.binder.FavoriteRecipesViewStateBinder
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.FavoriteRecipesNotificationPresentationToUiMapper
import ru.vdh.foodrecipes.favoriterecipes.ui.view.FavoriteRecipesFragment
import ru.vdh.foodrecipes.recipes.ui.view.RecipesFragment

@Module
@InstallIn(FragmentComponent::class)
class FavoriteRecipeUiModule {

    @Provides
    fun providesView(@ApplicationContext context: Context) = View(context)

    @Provides
    @Suppress("UNCHECKED_CAST")
    fun providesFavoriteRecipesViewStateBinder(fragment: Fragment) =
        FavoriteRecipesViewStateBinder(fragment as FavoriteRecipesFragment, fragment)
                as ViewStateBinder<FavoriteRecipesViewState, ViewsProvider>

    @Provides
    fun providesFavoriteRecipesNotificationPresentationToUiMapper() =
        FavoriteRecipesNotificationPresentationToUiMapper()
}