package ru.vdh.foodrecipes.recipedetails.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

