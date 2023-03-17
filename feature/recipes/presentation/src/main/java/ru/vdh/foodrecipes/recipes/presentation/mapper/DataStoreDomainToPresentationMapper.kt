package ru.vdh.foodrecipes.recipes.presentation.mapper

import ru.vdh.foodrecipes.recipes.domain.model.MealAndDietTypeDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.presentation.model.ExtendedIngredientPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.MealAndDietTypePresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.ResultPresentationModel

class DataStoreDomainToPresentationMapper {

    fun toPresentation(input: MealAndDietTypeDomainModel) =
        MealAndDietTypePresentationModel(
            input.selectedMealType,
            input.selectedMealTypeId,
            input.selectedDietType,
            input.selectedDietTypeId
        )
}