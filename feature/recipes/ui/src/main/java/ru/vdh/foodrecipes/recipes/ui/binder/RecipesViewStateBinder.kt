package ru.vdh.foodrecipes.recipes.ui.binder

import androidx.recyclerview.widget.LinearLayoutManager
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesViewState
import ru.vdh.foodrecipes.recipes.ui.adapter.RecipesAdapter
import ru.vdh.foodrecipes.recipes.ui.view.RecipesFragment
import ru.vdh.foodrecipes.recipes.ui.view.RecipesViewsProvider
import javax.inject.Inject

class RecipesViewStateBinder @Inject constructor(
    private val onToDoItemClickListener: OnClickListener,
    private val fragment: RecipesFragment,
) :
    ViewStateBinder<RecipesViewState, RecipesViewsProvider> {

    private val _onToDoItemClickListener: RecipesAdapter.OnClickListener =
        DelegateOnClickListener()

    override fun RecipesViewsProvider.bindState(viewState: RecipesViewState) {

        fragment.adapter.apply { onRecipeItemClickListener = _onToDoItemClickListener }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = fragment.adapter
            recyclerView.layoutManager = LinearLayoutManager(fragment.requireContext())
        }

    }

    private inner class DelegateOnClickListener : RecipesAdapter.OnClickListener {
        override fun onRecipeItemClick(recipeId: Int) {
            onToDoItemClickListener.onItemClick(recipeId)
        }
    }

    interface OnClickListener {
        fun onItemClick(recipeId: Int)
    }
}