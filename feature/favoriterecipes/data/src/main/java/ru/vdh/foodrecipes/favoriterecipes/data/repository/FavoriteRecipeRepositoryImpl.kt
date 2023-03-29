package ru.vdh.foodrecipes.favoriterecipes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.favoriterecipes.data.datasource.FavoriteRecipeDataSource
import ru.vdh.foodrecipes.favoriterecipes.data.mapper.FavoriteRecipeDataToDomainMapper
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel
import ru.vdh.foodrecipes.favoriterecipes.domain.repository.FavoriteRecipeRepository

class FavoriteRecipeRepositoryImpl(
    private val favoriteRecipeDataSource: FavoriteRecipeDataSource,
    private val favoriteRecipeDataToDomainMapper: FavoriteRecipeDataToDomainMapper,
) : FavoriteRecipeRepository {

    override fun getFavoriteRecipes(): Flow<List<FavoritesDomainModel>> =
        favoriteRecipeDataSource.getFavoriteRecipes().map { list ->
            list.map(favoriteRecipeDataToDomainMapper::toDomain)
        }
}


