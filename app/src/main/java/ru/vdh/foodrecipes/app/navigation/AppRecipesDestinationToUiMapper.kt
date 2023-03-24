package ru.vdh.foodrecipes.app.navigation

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import ru.vdh.foodrecipes.NavGraphDirections
import ru.vdh.foodrecipes.R
import ru.vdh.foodrecipes.core.presentation.model.PresentationDestination
import ru.vdh.foodrecipes.core.ui.navigation.model.UiDestination
import ru.vdh.foodrecipes.navigation.mapper.GlobalDestinationToUiMapper
import ru.vdh.foodrecipes.recipedetails.ui.mapper.NewFeatureDestinationToUiMapper.RecipeDetailsUiDestination
import ru.vdh.foodrecipes.recipes.presentation.destination.NewFeaturePresentationDestination.RecipeDetails
import ru.vdh.foodrecipes.recipes.ui.mapper.RecipesDestinationToUiMapper

class AppRecipesDestinationToUiMapper(
    private val activity: FragmentActivity,
    private val globalDestinationToUiMapper: GlobalDestinationToUiMapper
) : RecipesDestinationToUiMapper {
    override fun toUi(
        input: PresentationDestination
    ): UiDestination = when (input) {
        is RecipeDetails -> AppRecipeDetails(activity, input.id)
        else -> globalDestinationToUiMapper.toUi(input)
    }

    private data class AppRecipeDetails(
        private val activity: FragmentActivity,
        override val id: Int
    ) : RecipeDetailsUiDestination(id) {
        override fun navigate() {

            val currentFragment =
                activity.supportFragmentManager.findFragmentById(R.id.navHostFragment)

            currentFragment?.findNavController()
                ?.navigate(NavGraphDirections.actionGlobalToNavRecipeDetails(id))
            Log.d("AAA", "Add button clicked!!!")
        }
    }

}
