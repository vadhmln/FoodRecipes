package ru.vdh.foodrecipes.recipes.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.database.RecipesDao
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.recipes.data.datasource.LocalDataSource
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDataToDatabaseMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesDatabaseToDataMapper
import ru.vdh.foodrecipes.recipes.datasource.mapper.RecipesLocalDatabaseToDataMapper

class LocalDataSourceImpl(
    private val recipesDao: RecipesDao,
    private val recipesLocalDatabaseToDataMapper: RecipesLocalDatabaseToDataMapper,
    private val recipesDataToDatabaseMapper: RecipesDataToDatabaseMapper
) : LocalDataSource {

    override suspend fun readDatabase()=
        recipesDao.readRecipes().map(recipesLocalDatabaseToDataMapper::toData)

    override suspend fun insertRecipes(recipesEntity: RecipesDataModel) {
        recipesDao.insertRecipes(recipesDataToDatabaseMapper.toDatabase(recipesEntity))
    }
}