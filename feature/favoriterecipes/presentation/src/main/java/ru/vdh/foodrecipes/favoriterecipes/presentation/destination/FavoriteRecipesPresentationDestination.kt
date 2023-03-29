package ru.vdh.foodrecipes.favoriterecipes.presentation.destination

import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination

sealed interface FavoriteRecipesPresentationDestination: PresentationDestination {

    data class RecipeDetails(
        val id: Int
    ) : FavoriteRecipesPresentationDestination
}