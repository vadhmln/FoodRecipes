package ru.vdh.foodrecipes.foodjoke.ui.mapper

import ru.vdh.foodrecipes.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.model.NotificationUiModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeaturePresentationNotification

class FoodJokeNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<NewFeaturePresentationNotification> {
    override fun toUi(
        presentationNotification: NewFeaturePresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
