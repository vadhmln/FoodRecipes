package ru.vdh.foodrecipes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.vdh.foodrecipes.database.entities.FavoritesEntity
import ru.vdh.foodrecipes.database.entities.FoodJokeEntity
import ru.vdh.foodrecipes.database.entities.RecipesEntity
import ru.vdh.foodrecipes.database.entities.ResultEntity

@Database(
    entities = [
        RecipesEntity::class,
        FavoritesEntity::class,
        FoodJokeEntity::class,
        ResultEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class, Converter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}