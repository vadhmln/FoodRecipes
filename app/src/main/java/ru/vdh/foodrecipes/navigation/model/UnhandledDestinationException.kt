package ru.vdh.foodrecipes.navigation.model

import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination

class UnhandledDestinationException(
    destination: PresentationDestination
) : IllegalArgumentException(
    "Navigation to ${destination::class.simpleName} was not handled."
)
