package ru.vdh.foodrecipes.favoriterecipes.presentation.model

data class FavoriteRecipesViewState(
    val saveResult: Boolean = false,
    val firstName: String = "",
    val lastName: String = ""
)