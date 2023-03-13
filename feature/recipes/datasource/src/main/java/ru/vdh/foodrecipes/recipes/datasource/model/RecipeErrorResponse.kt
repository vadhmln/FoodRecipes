package ru.vdh.foodrecipes.recipes.datasource.model

/**
 * A customized recipe error response.
 *
 * @param code A network response code.
 * @param message A network error message.
 */
data class RecipeErrorResponse(
  val code: Int,
  var message: String?
)
