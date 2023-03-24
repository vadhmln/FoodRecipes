package ru.vdh.foodrecipes.recipes.ui.adapter

import javax.inject.Inject

class DelegateOnClickListener @Inject constructor() : RecipesAdapter.OnClickListener {
    var onToDoItemClickListener: RecipesAdapter.OnClickListener =
        RecipesAdapter.OnClickListener.DoNothing

    override fun onRecipeItemClick(recipeId: Int) {
        onToDoItemClickListener.onRecipeItemClick(recipeId)
    }
}