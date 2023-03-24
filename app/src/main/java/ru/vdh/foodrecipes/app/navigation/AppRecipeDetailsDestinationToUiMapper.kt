package ru.vdh.foodrecipes.app.navigation

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import ru.vdh.foodrecipes.NavGraphDirections
import ru.vdh.foodrecipes.R
import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination
import ru.vdh.foodrecipes.navigation.mapper.GlobalDestinationToUiMapper
import ru.vdh.foodrecipes.recipedetails.presentation.destination.NewFeaturePresentationDestination
import ru.vdh.foodrecipes.recipedetails.ui.mapper.NewFeatureDestinationToUiMapper


class AppRecipeDetailsDestinationToUiMapper(
    private val activity: FragmentActivity,
    private val globalDestinationToUiMapper: GlobalDestinationToUiMapper
) : NewFeatureDestinationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {

        else -> globalDestinationToUiMapper.toUi(input)
    }



}
