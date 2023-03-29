package ru.vdh.foodrecipes.recipedetails.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.recipedetails.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipedetails.presentation.model.ResultPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.viewmodel.RecipeDetailsViewModel
import ru.vdh.foodrecipes.recipedetails.ui.R
import ru.vdh.foodrecipes.recipedetails.ui.adapter.PagerAdapter
import ru.vdh.foodrecipes.recipedetails.ui.databinding.RecipeDetailsFragmentBinding
import ru.vdh.foodrecipes.recipedetails.ui.mapper.NewFeatureDestinationToUiMapper
import ru.vdh.foodrecipes.recipedetails.ui.mapper.NewUserNotificationPresentationToUiMapper
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsFragment :
    BaseFragment<NewFeatureViewState, NewFeaturePresentationNotification>(),
    NewFeatureViewsProvider {

    private var _binding: RecipeDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<RecipeDetailsFragmentArgs>()
    val bundle = Bundle()

    override val viewModel: RecipeDetailsViewModel by viewModels()

    override val layoutResourceId = R.layout.recipe_details_fragment

    private var recipeSaved = false
    private var savedRecipeId = 0

    @Inject
    override lateinit var destinationMapper:
            NewFeatureDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
            NewUserNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
            ViewStateBinder<NewFeatureViewState, ViewsProvider>

    override fun View.bindViews() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        Log.e("RecipeDetailsFragment", "RecipeDetailsFragment created!!!")

        _binding = RecipeDetailsFragmentBinding.inflate(inflater, container, false)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val pagerAdapter = PagerAdapter(
            bundle,
            fragments,
            this
        )
        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = titles[position]
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("RecipeDetailsFragment", "onViewCreatedBeginning ${args.recipeId}")
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.details_menu, menu)
                val favoriteItem = menu.findItem(R.id.save_to_favorites_menu)
                checkSavedRecipes(favoriteItem)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                when {
                    menuItem.itemId == R.id.save_to_favorites_menu && !recipeSaved -> {
                        saveToFavorites(menuItem)
                        true
                    }

                    menuItem.itemId == R.id.save_to_favorites_menu && recipeSaved -> {
                        removeFromFavorites(menuItem)
                        true
                    }

                    else -> {
                        false
                    }
                }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun checkSavedRecipes(menuItem: MenuItem): Boolean {
        viewModel.readFavoriteRecipes.observe(this) { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    viewModel.recipesLiveData.observe(viewLifecycleOwner) { result ->
                        result?.let {
                            val favoriteRecipeId = result.recipeId
                            if (savedRecipe.result.recipeId == favoriteRecipeId) {
                                changeMenuItemColor(
                                    menuItem,
                                    ru.vdh.cleanarch.core.ui.R.color.yellow
                                )
                                savedRecipeId = savedRecipe.id
                                recipeSaved = true
                            }
                            Log.d(
                                "RecipeDetailsFragment",
                                "${savedRecipe.result.recipeId}, $favoriteRecipeId"
                            )
                        }

                    }
                }
            } catch (e: Exception) {
                Log.d("RecipeDetailsFragment", e.message.toString())
            }
        }
        Log.d("savedRecipeId", savedRecipeId.toString())
        return true
    }

    private fun saveToFavorites(item: MenuItem) {

        viewModel.recipesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                val favoriteRecipe = FavoritesPresentationModel(
                    0,
                    ResultPresentationModel(
                        it.aggregateLikes,
                        it.cheap,
                        it.dairyFree,
                        it.extendedIngredients,
                        it.glutenFree,
                        it.recipeId,
                        it.image,
                        it.readyInMinutes,
                        it.sourceName,
                        it.sourceUrl,
                        it.summary,
                        it.title,
                        it.vegan,
                        it.vegetarian,
                        it.veryHealthy,
                    )
                )
                viewModel.insertFavoriteRecipe(favoriteRecipe)
                Log.d("RecipeDetailsFragment", it.title)
            }
        }
        changeMenuItemColor(item, ru.vdh.cleanarch.core.ui.R.color.yellow)
        showSnackBar("Recipe saved.")
        recipeSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        viewModel.recipesLiveData.observe(viewLifecycleOwner) { result ->
            result?.let {
                val favoriteRecipe = FavoritesPresentationModel(
                    savedRecipeId,
                    ResultPresentationModel(
                        it.aggregateLikes,
                        it.cheap,
                        it.dairyFree,
                        it.extendedIngredients,
                        it.glutenFree,
                        it.recipeId,
                        it.image,
                        it.readyInMinutes,
                        it.sourceName,
                        it.sourceUrl,
                        it.summary,
                        it.title,
                        it.vegan,
                        it.vegetarian,
                        it.veryHealthy,
                    )
                )
                viewModel.deleteFavoriteRecipe(favoriteRecipe)
                Log.d("RecipeDetailsFragment", it.title)
            }
        }
        changeMenuItemColor(item, ru.vdh.cleanarch.core.ui.R.color.white)
        showSnackBar("Removed from Favorites.")
        recipeSaved = false
        Log.d("savedRecipeId", savedRecipeId.toString())
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.detailsLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(requireContext(), color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

