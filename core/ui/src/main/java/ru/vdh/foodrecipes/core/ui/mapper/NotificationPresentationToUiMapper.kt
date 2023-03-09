package ru.vdh.foodrecipes.core.ui.mapper

import ru.vdh.foodrecipes.core.ui.model.NotificationUiModel

interface NotificationPresentationToUiMapper<PRESENTATION_NOTIFICATION : Any> {
    fun toUi(presentationNotification: PRESENTATION_NOTIFICATION): NotificationUiModel
}
