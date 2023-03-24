package ru.vdh.foodrecipes.recipedetails.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipedetails.ui.view.NewFeatureViewsProvider

class NewFeatureViewStateBinder :
    ViewStateBinder<NewFeatureViewState, NewFeatureViewsProvider> {
    override fun NewFeatureViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}