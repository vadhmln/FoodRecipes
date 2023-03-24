package ru.vdh.foodrecipes.recipedetails.data.repository

import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.data.mapper.RecipesDataToDomainMapper
import ru.vdh.foodrecipes.recipedetails.domain.repository.RecipeDetailsRepository

class RecipeDetailsRepositoryImpl(
    private val recipeDetailsDataSource: RecipeDetailsDataSource,
    private val recipesDataToDomainMapper: RecipesDataToDomainMapper
) : RecipeDetailsRepository {

    override fun getItemById(toDoId: Int?) =
        recipeDetailsDataSource.getItemById(toDoId).map(recipesDataToDomainMapper::toDomain)

}


