package ru.vdh.foodrecipes.newfeature.presentation.model

data class NewFeatureViewState(
    val saveResult: Boolean = false,
    val firstName: String = "",
    val lastName: String = ""
)