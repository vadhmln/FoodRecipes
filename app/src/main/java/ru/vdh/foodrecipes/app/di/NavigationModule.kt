package ru.vdh.foodrecipes.app.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.vdh.foodrecipes.R
import ru.vdh.foodrecipes.app.navigation.AppFoodJokeDestinationToUiMapper
import ru.vdh.foodrecipes.app.navigation.AppRecipeDetailsDestinationToUiMapper
import ru.vdh.foodrecipes.app.navigation.AppRecipesDestinationToUiMapper
import ru.vdh.foodrecipes.app.navigation.AppFavoriteRecipesDestinationToUiMapper
import ru.vdh.foodrecipes.navigation.mapper.GlobalDestinationToUiMapper
import ru.vdh.foodrecipes.recipes.ui.mapper.RecipesDestinationToUiMapper
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.FavoriteRecipesDestinationToUiMapper
import ru.vdh.foodrecipes.foodjoke.ui.mapper.FoodJokeDestinationToUiMapper
import ru.vdh.foodrecipes.recipedetails.ui.mapper.NewFeatureDestinationToUiMapper

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {
    @Provides
    fun providesSupportFragmentManager(activity: Activity) =
        (activity as AppCompatActivity).supportFragmentManager

    @Provides
    fun providesNavHostFragment(fragmentManager: FragmentManager) =
        fragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

    @Provides
    fun providesNavController(navHostFragment: NavHostFragment) =
        navHostFragment.navController

    @Provides
    fun providesGlobalDestinationToUiMapper(
        lazyNavController: Lazy<NavController>
    ) = GlobalDestinationToUiMapper(lazyNavController)

    @Provides
    fun providesAppNewFeatureDestinationToUiMapper(
        activity: FragmentActivity,
        globalDestinationToUiMapper: GlobalDestinationToUiMapper
    ): RecipesDestinationToUiMapper =
        AppRecipesDestinationToUiMapper(activity, globalDestinationToUiMapper)

    @Provides
    fun providesAppSecondFeatureDestinationToUiMapper(
        activity: FragmentActivity,
        globalDestinationToUiMapper: GlobalDestinationToUiMapper
    ): FavoriteRecipesDestinationToUiMapper =
        AppFavoriteRecipesDestinationToUiMapper(activity, globalDestinationToUiMapper)

    @Provides
    fun providesAppFoodJokeDestinationToUiMapper(
        activity: FragmentActivity,
        globalDestinationToUiMapper: GlobalDestinationToUiMapper
    ): FoodJokeDestinationToUiMapper =
        AppFoodJokeDestinationToUiMapper(activity, globalDestinationToUiMapper)

    @Provides
    fun providesAppRecipeDetailsDestinationToUiMapper(
        activity: FragmentActivity,
        globalDestinationToUiMapper: GlobalDestinationToUiMapper
    ): NewFeatureDestinationToUiMapper =
        AppRecipeDetailsDestinationToUiMapper(activity, globalDestinationToUiMapper)
}
