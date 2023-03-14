package ru.vdh.foodrecipes.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vdh.foodrecipes.common.utils.Constants.Companion.FOOD_JOKE_TABLE
import ru.vdh.foodrecipes.network.model.FoodJokeRemoteDataSourceModel

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJokeRemoteDataSourceModel
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}