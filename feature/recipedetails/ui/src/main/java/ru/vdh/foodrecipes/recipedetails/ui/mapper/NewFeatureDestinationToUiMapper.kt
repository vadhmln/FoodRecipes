package ru.vdh.foodrecipes.recipedetails.ui.mapper

import ru.vdh.foodrecipes.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

interface NewFeatureDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class RecipeDetailsUiDestination(
        open val id: Int
    ) : UiDestination
}
