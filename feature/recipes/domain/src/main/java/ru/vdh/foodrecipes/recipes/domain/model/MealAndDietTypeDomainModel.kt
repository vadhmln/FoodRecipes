package ru.vdh.foodrecipes.recipes.domain.model

data class MealAndDietTypeDomainModel(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)
