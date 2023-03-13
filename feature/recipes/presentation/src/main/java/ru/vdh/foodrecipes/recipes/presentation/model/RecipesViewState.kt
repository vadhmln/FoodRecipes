package ru.vdh.foodrecipes.recipes.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class RecipesViewState (
    val isLoading: Boolean = false,
    val recipesList: LiveData<RecipesPresentationModel> = MutableLiveData()
) {
    fun loading(): RecipesViewState = copy(isLoading = true)

    fun withRecipesList(recipesListData: LiveData<RecipesPresentationModel>) = copy(
        isLoading = false,
        recipesList = recipesListData
    )
}
