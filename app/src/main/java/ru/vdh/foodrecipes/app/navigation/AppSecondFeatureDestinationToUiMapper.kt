package ru.vdh.foodrecipes.app.navigation

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import ru.vdh.foodrecipes.NavGraphDirections
import ru.vdh.foodrecipes.R
import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination
import ru.vdh.foodrecipes.navigation.mapper.GlobalDestinationToUiMapper
import ru.vdh.foodrecipes.favoriterecipes.presentation.destination.SecondFeaturePresentationDestination.NewFeature
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.SecondFeatureDestinationToUiMapper
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.SecondFeatureDestinationToUiMapper.NewFeatureUiDestination

class AppSecondFeatureDestinationToUiMapper(
    private val activity: FragmentActivity,
    private val globalDestinationToUiMapper: GlobalDestinationToUiMapper
) : SecondFeatureDestinationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {
        is NewFeature -> AppNewFeature(activity, input.id)
        else -> globalDestinationToUiMapper.toUi(input)
    }

    private data class AppNewFeature(
        private val activity: FragmentActivity,
        override val id: Int
    ) : NewFeatureUiDestination(id) {
        override fun navigate() {

            val currentFragment =
                activity.supportFragmentManager.findFragmentById(R.id.navHostFragment)

            currentFragment?.findNavController()
//                ?.navigate(NavGraphDirections.actionGlobalToNavNewFeature())
            Log.d("AAA", "Add button clicked!!!")
        }
    }

}
