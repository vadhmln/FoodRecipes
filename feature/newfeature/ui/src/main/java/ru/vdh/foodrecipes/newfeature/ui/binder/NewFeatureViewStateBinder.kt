package ru.vdh.foodrecipes.newfeature.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.newfeature.ui.view.NewFeatureViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, NewFeatureViewsProvider> {
    override fun NewFeatureViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}