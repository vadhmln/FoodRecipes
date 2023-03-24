package ru.vdh.foodrecipes.recipedetails.datasource

import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.recipedetails.data.datasource.RecipeDetailsDataSource
import ru.vdh.foodrecipes.recipedetails.datasource.mapper.RecipesLocalDatabaseToDataMapper

class RecipeDetailsDataSourceImpl(
    private val recipesDao: RecipesDao,
    private val recipesLocalDatabaseToDataMapper: RecipesLocalDatabaseToDataMapper

    ) : RecipeDetailsDataSource {
    override fun getItemById(toDoId: Int?) =
        recipesDao.getItemById(toDoId).map(recipesLocalDatabaseToDataMapper::toData)

}