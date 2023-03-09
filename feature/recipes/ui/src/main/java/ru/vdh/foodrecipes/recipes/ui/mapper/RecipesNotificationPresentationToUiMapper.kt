package ru.vdh.foodrecipes.recipes.ui.mapper

import ru.vdh.foodrecipes.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.model.NotificationUiModel
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationNotification

class RecipesNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<NewFeaturePresentationNotification> {
    override fun toUi(
        presentationNotification: NewFeaturePresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
