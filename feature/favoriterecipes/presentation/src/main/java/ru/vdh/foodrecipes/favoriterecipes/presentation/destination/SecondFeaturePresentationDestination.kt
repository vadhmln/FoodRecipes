package ru.vdh.foodrecipes.favoriterecipes.presentation.destination

import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination

sealed interface SecondFeaturePresentationDestination: PresentationDestination {

    data class NewFeature(
        val id: Int
    ) : SecondFeaturePresentationDestination
}