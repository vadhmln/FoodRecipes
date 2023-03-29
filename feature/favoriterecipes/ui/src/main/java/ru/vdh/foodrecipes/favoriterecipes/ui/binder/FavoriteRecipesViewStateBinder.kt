package ru.vdh.foodrecipes.favoriterecipes.ui.binder

import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesViewState
import ru.vdh.foodrecipes.favoriterecipes.ui.adapter.FavoriteRecipesAdapter
import ru.vdh.foodrecipes.favoriterecipes.ui.view.FavoriteRecipesFragment
import ru.vdh.foodrecipes.favoriterecipes.ui.view.FavoriteRecipesViewsProvider

class FavoriteRecipesViewStateBinder(
    private val onFavoriteRecipeClickListener: OnClickListener,
    private val fragment: FavoriteRecipesFragment,
) : ViewStateBinder<FavoriteRecipesViewState, FavoriteRecipesViewsProvider> {

    private val _onFavoriteRecipeClickListener: FavoriteRecipesAdapter.OnClickListener =
        DelegateOnClickListener()

    override fun FavoriteRecipesViewsProvider.bindState(viewState: FavoriteRecipesViewState) {

        fragment.adapter.apply { onFavoriteRecipeClickListener = _onFavoriteRecipeClickListener }
    }

    private inner class DelegateOnClickListener : FavoriteRecipesAdapter.OnClickListener {
        override fun onFavoriteRecipeClick(recipeId: Int) {
            onFavoriteRecipeClickListener.onItemClick(recipeId)
        }
    }

    interface OnClickListener {
        fun onItemClick(recipeId: Int)
    }
}