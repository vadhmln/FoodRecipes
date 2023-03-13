package ru.vdh.foodrecipes.recipes.ui.binder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
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

        fragment.adapter.apply { onToDoItemClickListener = _onToDoItemClickListener }

        if (recyclerView.adapter == null) {
            recyclerView.adapter = fragment.adapter
            recyclerView.layoutManager = LinearLayoutManager(fragment.requireContext())

//            shimmerFrameLayout.startShimmer()
//            shimmerFrameLayout.visibility = View.VISIBLE
//            shimmerFrameLayout.visibility = View.GONE
        }

    }

    private inner class DelegateOnClickListener : RecipesAdapter.OnClickListener {
        override fun onToDoItemClick(toDoId: Int) {
            onToDoItemClickListener.onItemClick(toDoId)
        }
    }

    interface OnClickListener {
        fun onItemClick(toDoId: Int)
    }
}