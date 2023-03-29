package ru.vdh.foodrecipes.favoriterecipes.ui.adapter

import javax.inject.Inject

class DelegateOnClickListener @Inject constructor() : FavoriteRecipesAdapter.OnClickListener {
    var onFavoriteRecipeClickListener: FavoriteRecipesAdapter.OnClickListener =
        FavoriteRecipesAdapter.OnClickListener.DoNothing

    override fun onFavoriteRecipeClick(recipeId: Int) {
        onFavoriteRecipeClickListener.onFavoriteRecipeClick(recipeId)
    }
}