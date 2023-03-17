package ru.vdh.foodrecipes.recipes.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vdh.foodrecipes.recipes.domain.model.MealAndDietTypeDomainModel

interface DataStoreRepository {

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )

    suspend fun saveBackOnline(backOnline: Boolean)

    val readMealAndDietType: Flow<MealAndDietTypeDomainModel>

    val readBackOnline: Flow<Boolean>
}