package ru.vdh.foodrecipes.recipes.presentation.model

data class MealAndDietTypePresentationModel(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)