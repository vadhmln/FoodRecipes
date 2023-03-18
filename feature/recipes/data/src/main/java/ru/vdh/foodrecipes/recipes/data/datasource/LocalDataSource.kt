package ru.vdh.foodrecipes.recipes.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel

interface LocalDataSource {

    suspend fun readDatabase(): List<RecipesDataModel>

    suspend fun insertRecipes(recipesEntity: RecipesDataModel)
}