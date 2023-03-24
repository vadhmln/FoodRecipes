package ru.vdh.foodrecipes.database

import androidx.room.*
import ru.vdh.foodrecipes.database.entities.FavoritesEntity
import ru.vdh.foodrecipes.database.entities.FoodJokeEntity
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.database.entities.ResultEntity
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResultRecipes(resultEntity: ResultEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    suspend fun readRecipes(): List<RecipesEntity>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Query("SELECT * FROM food_joke_table ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

    @Query("SELECT * FROM result_entity WHERE recipeId = :id")
    fun getItemById(id: Int?) : Flow<ResultEntity>

}