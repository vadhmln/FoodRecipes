package ru.vdh.foodrecipes.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vdh.foodrecipes.common.utils.Constants.Companion.RECIPES_TABLE
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: RecipesRemoteDataSourceModel
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}