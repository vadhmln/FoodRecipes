package ru.vdh.foodrecipes.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.vdh.foodrecipes.common.utils.Constants.Companion.FAVORITE_RECIPES_TABLE
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: ResultRemoteDataSourceModel
)