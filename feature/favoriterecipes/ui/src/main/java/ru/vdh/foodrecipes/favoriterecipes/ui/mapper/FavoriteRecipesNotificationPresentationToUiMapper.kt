package ru.vdh.foodrecipes.favoriterecipes.ui.mapper

import ru.vdh.foodrecipes.core.ui.mapper.NotificationPresentationToUiMapper
import ru.vdh.foodrecipes.core.ui.model.NotificationUiModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesPresentationNotification

class FavoriteRecipesNotificationPresentationToUiMapper :
    NotificationPresentationToUiMapper<FavoriteRecipesPresentationNotification> {
    override fun toUi(
        presentationNotification: FavoriteRecipesPresentationNotification
    ): NotificationUiModel {
        throw IllegalStateException("Notifications not supported.")
    }
}
