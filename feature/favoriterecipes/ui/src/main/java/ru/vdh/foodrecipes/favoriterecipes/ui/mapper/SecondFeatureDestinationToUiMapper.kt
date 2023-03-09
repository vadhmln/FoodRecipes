package ru.vdh.foodrecipes.favoriterecipes.ui.mapper

import ru.vdh.foodrecipes.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

interface SecondFeatureDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class NewFeatureUiDestination(
        open val id: Int
    ) : UiDestination
}
