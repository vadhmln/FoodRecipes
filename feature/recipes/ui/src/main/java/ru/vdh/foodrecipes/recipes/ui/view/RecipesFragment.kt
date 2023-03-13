package ru.vdh.foodrecipes.recipes.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesViewState
import ru.vdh.foodrecipes.recipes.presentation.viewmodel.RecipesFragmentViewModel
import ru.vdh.foodrecipes.recipes.ui.mapper.RecipesDestinationToUiMapper
import ru.vdh.foodrecipes.recipes.ui.mapper.RecipesNotificationPresentationToUiMapper
import ru.vdh.foodrecipes.recipes.ui.R
import ru.vdh.foodrecipes.recipes.ui.adapter.RecipesAdapter
import ru.vdh.foodrecipes.recipes.ui.binder.RecipesViewStateBinder
import ru.vdh.foodrecipes.recipes.ui.databinding.RecipesFragmentBinding
import javax.inject.Inject

private const val NO_LAYOUT_RESOURCE = 0

@AndroidEntryPoint
class RecipesFragment :
    BaseFragment<RecipesViewState, NewFeaturePresentationNotification>(),
    RecipesViewsProvider,
    RecipesViewStateBinder.OnClickListener {

    private var _binding: RecipesFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val adapter by lazy { RecipesAdapter() }

    override val viewModel: RecipesFragmentViewModel by viewModels()

    override val layoutResourceId = R.layout.recipes_fragment

    @Inject
    override lateinit var destinationMapper:
            RecipesDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
            RecipesNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
            ViewStateBinder<RecipesViewState, ViewsProvider>

    override fun View.bindViews() {

    }

    override lateinit var recyclerView: RecyclerView

    override lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        Log.e("AAA", "RecipesFragment created!!!")

        _binding = RecipesFragmentBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerview
        shimmerFrameLayout = binding.shimmerFrameLayout

        viewModel.getRecipesSafeCall(viewModel.applyQueries())
        viewModel.recipesResponse.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
            hideShimmerEffect()
            Log.d("AAA", "$data")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    private fun requestApiData() {
//        Log.d("RecipesFragment", "requestApiData called!")
//        viewModel.getRecipes(viewModel.applyQueries())
//        viewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is RecipesViewState.Success -> {
//                    hideShimmerEffect()
//                    response.data?.let { adapter.setData(it) }
//                    viewModel.saveMealAndDietType()
//                }
//                is RecipesViewState.Error -> {
//                    hideShimmerEffect()
//                    loadDataFromCache()
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                is RecipesViewState.Loading -> {
//                    showShimmerEffect()
//                }
//            }
//        }
//    }

    private fun loadDataFromCache() {
//        viewModel.readRecipes.observe(viewLifecycleOwner) { database ->
//            if (database.isNotEmpty()) {
//                adapter.setData(database.first().foodRecipe)
//            }
//        }
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
    }

    override fun onItemClick(toDoId: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}