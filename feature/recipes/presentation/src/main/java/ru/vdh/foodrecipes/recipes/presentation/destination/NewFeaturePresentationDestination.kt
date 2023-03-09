package ru.vdh.foodrecipes.recipes.presentation.destination

import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination

sealed interface NewFeaturePresentationDestination: PresentationDestination {

    data class SecondFeature(
        val id: Int
    ) : NewFeaturePresentationDestination
}