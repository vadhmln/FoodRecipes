package ru.vdh.foodrecipes.favoriterecipes.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.favoriterecipes.domain.model.FavoritesDomainModel

interface FavoriteRecipeRepository {

    fun getFavoriteRecipes(): Flow<List<FavoritesDomainModel>>
}