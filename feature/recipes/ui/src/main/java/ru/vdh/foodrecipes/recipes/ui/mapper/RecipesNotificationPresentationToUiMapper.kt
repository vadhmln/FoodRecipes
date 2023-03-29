package ru.vdh.foodrecipes.recipes.ui.mapper

import ru.vdh.foodrecipes.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.model.NotificationUiModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationNotification

class RecipesNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<RecipesPresentationNotification> {
    override fun toUi(
        presentationNotification: RecipesPresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
