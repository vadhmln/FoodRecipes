package ru.vdh.foodrecipes.recipes.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.vdh.foodrecipes.common.utils.NetworkListener
import ru.vdh.foodrecipes.common.utils.observeOnce
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
    RecipesViewStateBinder.OnClickListener,
    SearchView.OnQueryTextListener {

    private var _binding: RecipesFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args by navArgs<RecipesFragmentArgs>()

    val adapter by lazy { RecipesAdapter() }

    override val viewModel: RecipesFragmentViewModel by viewModels()

    private lateinit var networkListener: NetworkListener

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

    override lateinit var errorImageView: ImageView

    override lateinit var errorTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        Log.e("AAA", "RecipesFragment created!!!")

        _binding = RecipesFragmentBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.recipes_menu, menu)

                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this@RecipesFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        recyclerView = binding.recyclerview
        shimmerFrameLayout = binding.shimmerFrameLayout
        errorImageView = binding.errorImageView
        errorTextView = binding.errorTextView

        viewModel.readBackOnline.observe(viewLifecycleOwner) {
            viewModel.backOnline = it
        }

        lifecycleScope.launchWhenStarted {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(requireContext())
                .collect { status ->
                    Log.d("NetworkListener", status.toString())
                    viewModel.networkStatus = status
                    viewModel.showNetworkStatus()
                    readDatabase()
                }
        }

        binding.recipesFab.setOnClickListener {
            if (viewModel.networkStatus) {
                findNavController().navigate(RecipesFragmentDirections.actionRecipesFragmentToRecipesBottomSheet())
            } else {
                viewModel.showNetworkStatus()
            }
        }

        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchApiData(query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            viewModel.readRecipes.observeOnce(viewLifecycleOwner) { responseData ->
                if (responseData.results.isEmpty()) {
                    viewModel.errorMassage = "Recipes not found - readDatabase."
                    handleRequestErrors(errorImageView)
                    handleRequestErrors(errorTextView)
                    recyclerView.visibility = View.INVISIBLE
                    Log.d("RecipesFragment", viewModel.errorMassage)
                }
                if (!args.backFromBottomSheet && responseData.results.isNotEmpty()) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    adapter.setData(responseData)
                    hideShimmerEffect()
                } else if (viewModel.hasInternetConnection()) {
                    requestApiData()
                } else {
                    viewModel.errorMassage = "No Internet Connection."
                    handleRequestErrors(errorImageView)
                    handleRequestErrors(errorTextView)
                    Log.d("RecipesFragment", viewModel.errorMassage)
                }
            }
        }
    }


    private fun requestApiData() {
        viewModel.getRecipes(viewModel.applyQueries())
        viewModel.recipesResponse.observe(viewLifecycleOwner) { responseData ->
            if (responseData.results.isEmpty()) {
                viewModel.errorMassage = "Recipes not found."
                handleRequestErrors(errorImageView)
                handleRequestErrors(errorTextView)
                recyclerView.visibility = View.INVISIBLE
            } else {
                adapter.setData(responseData)
                hideShimmerEffect()
                Log.d("RecipesFragment", "requestApiData called!")
            }
        }
    }

    private fun searchApiData(searchQuery: String) {
        Log.d("RecipesFragment", "searchApiData")
        if (viewModel.hasInternetConnection()) {
            showShimmerEffect()
            viewModel.searchRecipes(viewModel.applySearchQuery(searchQuery))

            viewModel.searchedRecipesResponse.observe(viewLifecycleOwner) { responseData ->
                if (responseData.results.isEmpty()) {
                    viewModel.errorMassage = "Recipes not found - searchApiData."
                    Toast.makeText(requireContext(), viewModel.errorMassage, Toast.LENGTH_SHORT)
                        .show()
                    hideShimmerEffect()
                    Log.d("RecipesFragment", viewModel.errorMassage)
                } else {
                    adapter.setData(responseData)
                    hideShimmerEffect()
                }
            }
        } else {
            viewModel.errorMassage = "No Internet Connection."
            Toast.makeText(requireContext(), viewModel.errorMassage, Toast.LENGTH_SHORT).show()
        }

    }

    private fun handleRequestErrors(view: View) {
        when (view) {
            is ImageView -> {
                if (viewModel.hasInternetConnection()) {
                    view.visibility = View.INVISIBLE

                } else {
                    hideShimmerEffect()
                    view.visibility = View.VISIBLE
                }
            }

            is TextView -> {
                if (viewModel.hasInternetConnection()) {
                    view.visibility = View.INVISIBLE

                } else {
                    hideShimmerEffect()
                    view.visibility = View.VISIBLE
                    view.text = viewModel.errorMassage
                }
            }
        }
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

    override fun onItemClick(recipeId: Int) {
        viewModel.onRecipeDetailsAction(recipeId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}