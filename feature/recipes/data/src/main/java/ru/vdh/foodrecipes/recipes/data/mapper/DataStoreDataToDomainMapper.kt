package ru.vdh.foodrecipes.recipes.data.mapper

import ru.vdh.foodrecipes.recipes.data.model.MealAndDietTypeDataModel
import ru.vdh.foodrecipes.recipes.data.model.RecipesDataModel
import ru.vdh.foodrecipes.recipes.domain.model.ExtendedIngredientDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.MealAndDietTypeDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.RecipesDomainModel
import ru.vdh.foodrecipes.recipes.domain.model.ResultDomainModel

class DataStoreDataToDomainMapper {

    fun toDomain(input: MealAndDietTypeDataModel) =
        MealAndDietTypeDomainModel(
            input.selectedMealType,
            input.selectedMealTypeId,
            input.selectedDietType,
            input.selectedDietTypeId
        )
}