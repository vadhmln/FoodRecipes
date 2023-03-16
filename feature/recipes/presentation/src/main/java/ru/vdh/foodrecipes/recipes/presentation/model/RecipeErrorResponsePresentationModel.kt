package ru.vdh.foodrecipes.recipes.presentation.model

data class RecipeErrorResponsePresentationModel(
  val code: Int,
  var message: String?
)
