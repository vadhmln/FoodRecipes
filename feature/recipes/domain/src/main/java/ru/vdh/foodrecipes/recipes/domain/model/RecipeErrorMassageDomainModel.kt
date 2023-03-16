package ru.vdh.foodrecipes.recipes.domain.model

data class RecipeErrorMassageDomainModel(
    val code: Int,
    var message: String?
)
