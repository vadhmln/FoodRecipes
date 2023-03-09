package ru.vdh.foodrecipes.favoriterecipes.ui.mapper

import ru.vdh.foodrecipes.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.model.NotificationUiModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeaturePresentationNotification

class NewUserNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<NewFeaturePresentationNotification> {
    override fun toUi(
        presentationNotification: NewFeaturePresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
