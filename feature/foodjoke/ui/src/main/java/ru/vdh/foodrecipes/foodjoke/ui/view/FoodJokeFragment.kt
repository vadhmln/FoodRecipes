package ru.vdh.foodrecipes.foodjoke.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.vdh.foodrecipes.common.utils.Constants.Companion.API_KEY
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.foodjoke.presentation.model.FoodJokePresentationModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.foodjoke.presentation.viewmodel.FoodJokeFragmentViewModel
import ru.vdh.foodrecipes.foodjoke.ui.R
import ru.vdh.foodrecipes.foodjoke.ui.databinding.FoodJokeFragmentBinding
import ru.vdh.foodrecipes.foodjoke.ui.mapper.FoodJokeDestinationToUiMapper
import ru.vdh.foodrecipes.foodjoke.ui.mapper.FoodJokeNotificationPresentationToUiMapper
import javax.inject.Inject

@AndroidEntryPoint
class FoodJokeFragment : BaseFragment<NewFeatureViewState, NewFeaturePresentationNotification>(),
    FoodJokeViewsProvider {

    private var _binding: FoodJokeFragmentBinding? = null
    private val binding get() = _binding!!

    override val viewModel: FoodJokeFragmentViewModel by viewModels()

    override val layoutResourceId = R.layout.food_joke_fragment

    @Inject
    override lateinit var destinationMapper:
            FoodJokeDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
            FoodJokeNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
            ViewStateBinder<NewFeatureViewState, ViewsProvider>

    private var foodJoke = "No Food Joke"

    override lateinit var progressBar: ProgressBar

    override lateinit var materialCardView: MaterialCardView

    override fun View.bindViews() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FoodJokeFragmentBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.food_joke_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.share_food_joke_menu) {
                    val shareIntent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(Intent.EXTRA_TEXT, foodJoke)
                        this.type = "text/plain"
                    }
                    startActivity(shareIntent)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        progressBar = binding.progressBar
        materialCardView = binding.foodJokeCardView

        readDatabase()

        return binding.root
    }

    private fun readDatabase() {
        viewModel.getFoodJoke(API_KEY)
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            viewModel.foodJokeResponse.observe(viewLifecycleOwner) { foodJokeText ->

                viewModel.readFoodJoke.observe(viewLifecycleOwner) { database ->
                    if (database.isNotEmpty()) {
                        progressBar.visibility = View.INVISIBLE
                        materialCardView.visibility = View.VISIBLE
                        binding.foodJokeTextView.text = foodJokeText.text
                        foodJoke = foodJokeText.text
                    } else loadDataFromCache()
                }
            }
        }
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            viewModel.readFoodJoke.observe(viewLifecycleOwner) { database ->
                if (!database.isNullOrEmpty()) {
                    binding.foodJokeTextView.text = database.first().foodJoke.text
                    foodJoke = database.first().foodJoke.text
                }
            }
        }
    }

    private fun setCardAndProgressVisibility(
        progressBar: View,
        materialCardView: View,
        database: List<FoodJokePresentationModel>?
    ) {
        if (database.isNullOrEmpty()) {
            progressBar.visibility = View.VISIBLE
            materialCardView.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            materialCardView.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}