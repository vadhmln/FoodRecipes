package ru.vdh.foodrecipes.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.vdh.foodrecipes.network.model.RecipesRemoteDataSourceModel
import ru.vdh.foodrecipes.network.model.ResultRemoteDataSourceModel

class RecipesTypeConverter {

    var gson = Gson()

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

}