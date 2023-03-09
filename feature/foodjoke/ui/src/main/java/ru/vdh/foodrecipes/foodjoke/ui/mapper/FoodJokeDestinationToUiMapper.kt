package ru.vdh.foodrecipes.foodjoke.ui.mapper

import ru.vdh.foodrecipes.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

interface FoodJokeDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class SecondFeatureUiDestination(
        open val id: Int
    ) : UiDestination
}
