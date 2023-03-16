package ru.vdh.foodrecipes.recipes.datasource.mapper

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import ru.vdh.foodrecipes.recipes.datasource.model.RecipeErrorResponseDataSourceModel

object ErrorResponseMapper : ApiErrorModelMapper<RecipeErrorResponseDataSourceModel> {

  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): RecipeErrorResponseDataSourceModel {
    return RecipeErrorResponseDataSourceModel(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
