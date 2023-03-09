package ru.vdh.foodrecipes.recipes.ui.mapper

import ru.vdh.foodrecipes.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

interface RecipesDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class SecondFeatureUiDestination(
        open val id: Int
    ) : UiDestination
}
