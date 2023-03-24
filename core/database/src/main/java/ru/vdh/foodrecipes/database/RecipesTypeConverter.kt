package ru.vdh.foodrecipes.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.vdh.foodrecipes.database.entities.ExtendedIngredientsEntity
import ru.vdh.foodrecipes.database.entities.ResultEntity
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel

class RecipesTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: RecipesRemoteDataSourceModel): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): RecipesRemoteDataSourceModel {
        val listType = object : TypeToken<RecipesRemoteDataSourceModel>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(result: ResultRemoteDataSourceModel): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): ResultRemoteDataSourceModel {
        val listType = object : TypeToken<ResultRemoteDataSourceModel>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultEntityToString(result: ResultEntity): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResultEntity(data: String): ResultEntity {
        val listType = object : TypeToken<ResultEntity>() {}.type
        return gson.fromJson(data, listType)
    }

}