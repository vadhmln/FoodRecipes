package ru.vdh.foodrecipes.recipes.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipes.ui.view.RecipesViewsProvider

class RecipesViewStateBinder :
    ViewStateBinder<NewFeatureViewState, RecipesViewsProvider> {
    override fun RecipesViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}