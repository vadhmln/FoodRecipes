package ru.vdh.foodrecipes.navigation.model

import androidx.navigation.NavController
import dagger.Lazy
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination

class BackUiDestination(
    private val lazyNavController: Lazy<NavController>
) : UiDestination {
    override fun navigate() {
        lazyNavController.get().popBackStack()
    }
}
