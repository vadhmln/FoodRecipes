package ru.vdh.foodrecipes.favoriterecipes.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesViewState
import ru.vdh.foodrecipes.favoriterecipes.ui.view.FavoriteRecipesViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<FavoriteRecipesViewState, FavoriteRecipesViewsProvider> {
    override fun FavoriteRecipesViewsProvider
            .bindState(viewState: FavoriteRecipesViewState) = Unit
}