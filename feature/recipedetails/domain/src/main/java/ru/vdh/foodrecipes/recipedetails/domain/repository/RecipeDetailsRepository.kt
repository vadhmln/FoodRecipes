package ru.vdh.foodrecipes.recipedetails.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipedetails.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipedetails.domain.model.ResultDomainModel

interface RecipeDetailsRepository {

    fun getItemById(toDoId: Int?): Flow<ResultDomainModel?>

}