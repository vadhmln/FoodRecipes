package ru.vdh.foodrecipes.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.vdh.foodrecipes.database.Converter

@Entity(tableName = "result_entity")
data class ResultEntity(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    @TypeConverters(Converter::class)
    val extendedIngredients: List<ExtendedIngredientsEntity>,
    val glutenFree: Boolean,
    @PrimaryKey(autoGenerate = false)
    val recipeId: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)