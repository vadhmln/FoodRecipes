package ru.vdh.foodrecipes.newfeature.ui.mapper

import ru.vdh.foodrecipes.core.ui.navigation.mapper.DestinationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

interface NewFeatureDestinationToUiMapper : DestinationPresentationToUiMapper {

    abstract class SecondFeatureUiDestination(
        open val id: Int
    ) : UiDestination
}
