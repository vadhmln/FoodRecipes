package ru.vdh.foodrecipes.foodjoke.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.foodjoke.ui.view.FoodJokeViewsProvider

class FoodJokeViewStateBinder :
    ViewStateBinder<NewFeatureViewState, FoodJokeViewsProvider> {
    override fun FoodJokeViewsProvider
            .bindState(viewState: NewFeatureViewState) = Unit
}