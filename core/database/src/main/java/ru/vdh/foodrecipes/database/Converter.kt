package ru.vdh.foodrecipes.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.vdh.foodrecipes.database.entities.ExtendedIngredientsEntity

class Converter {

    private var gson = Gson()

    @TypeConverter
    fun fromHobbies(extendedIngredients: List<ExtendedIngredientsEntity?>): String? {
        return gson.toJson(extendedIngredients)
    }

    @TypeConverter
    fun toHobbies(data: String): List<ExtendedIngredientsEntity>? {
        val listType = object : TypeToken<List<ExtendedIngredientsEntity>?>() {}.type
        return gson.fromJson(data, listType)
    }
}