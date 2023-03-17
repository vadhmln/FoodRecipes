package ru.vdh.foodrecipes.recipes.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipes.data.model.MealAndDietTypeDataModel

interface DataStoreDataSource {

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )

    suspend fun saveBackOnline(backOnline: Boolean)

    val readMealAndDietType: Flow<MealAndDietTypeDataModel>

    val readBackOnline: Flow<Boolean>
}