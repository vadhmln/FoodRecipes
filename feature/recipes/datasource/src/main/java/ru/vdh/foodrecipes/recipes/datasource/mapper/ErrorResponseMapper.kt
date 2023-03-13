package ru.vdh.foodrecipes.recipes.datasource.mapper

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import ru.vdh.foodrecipes.recipes.datasource.model.RecipeErrorResponse

object ErrorResponseMapper : ApiErrorModelMapper<RecipeErrorResponse> {

  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): RecipeErrorResponse {
    return RecipeErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
