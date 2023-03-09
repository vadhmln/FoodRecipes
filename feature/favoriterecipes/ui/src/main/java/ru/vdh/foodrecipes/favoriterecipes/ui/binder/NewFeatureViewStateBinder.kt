package ru.vdh.foodrecipes.favoriterecipes.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.favoriterecipes.ui.view.FavoriteRecipesViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, FavoriteRecipesViewsProvider> {
    override fun FavoriteRecipesViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}