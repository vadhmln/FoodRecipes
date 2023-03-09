package ru.vdh.foodrecipes.core.ui.navigation.mapper

import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

interface DestinationPresentationToUiMapper {
    fun toUi(input: PresentationDestination): UiDestination
}
