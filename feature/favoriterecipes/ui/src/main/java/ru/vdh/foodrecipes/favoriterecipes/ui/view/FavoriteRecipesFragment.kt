package ru.vdh.foodrecipes.favoriterecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesPresentationNotification
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesViewState
import ru.vdh.foodrecipes.favoriterecipes.presentation.viewmodel.FavoriteRecipesFragmentViewModel
import ru.vdh.foodrecipes.favoriterecipes.ui.R
import ru.vdh.foodrecipes.favoriterecipes.ui.adapter.FavoriteRecipesAdapter
import ru.vdh.foodrecipes.favoriterecipes.ui.binder.FavoriteRecipesViewStateBinder
import ru.vdh.foodrecipes.favoriterecipes.ui.databinding.FragmentFavoriteRecipesBinding
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.FavoriteRecipesNotificationPresentationToUiMapper
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.FavoriteRecipesDestinationToUiMapper
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteRecipesFragment :
    BaseFragment<FavoriteRecipesViewState, FavoriteRecipesPresentationNotification>(),
    FavoriteRecipesViewsProvider,
    FavoriteRecipesViewStateBinder.OnClickListener {

    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!

    override val viewModel: FavoriteRecipesFragmentViewModel by viewModels()

    val adapter: FavoriteRecipesAdapter by lazy {
        FavoriteRecipesAdapter(
            requireActivity(),
            viewModel,
        )
    }

    override val layoutResourceId = R.layout.fragment_favorite_recipes

    @Inject
    override lateinit var destinationMapper:
            FavoriteRecipesDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
            FavoriteRecipesNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
            ViewStateBinder<FavoriteRecipesViewState, ViewsProvider>

    override lateinit var noDataImageView: ImageView

    override lateinit var noDataTextView: TextView

    override lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.favorite_recipes_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.deleteAll_favorite_recipes_menu) {
                    viewModel.deleteAllFavoriteRecipes()
                    showSnackBar()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noDataImageView = binding.noDataImageView
        noDataTextView = binding.noDataTextView
        recyclerView = binding.favoriteRecipesRecyclerView

        setupRecyclerView(binding.favoriteRecipesRecyclerView)

        viewModel.readFavoriteRecipes.observe(viewLifecycleOwner) {
            setViewVisibility(noDataImageView, noDataTextView, it, adapter)
        }

        return binding.root
    }

    override fun View.bindViews() {

    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showSnackBar() {
        Snackbar.make(
            binding.root,
            "All recipes removed.",
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()
    }

    private fun setViewVisibility(
        firstView: View,
        secondView: View,
        favoritesEntity: List<FavoritesPresentationModel>?,
        mAdapter: FavoriteRecipesAdapter?
    ) {

        if (favoritesEntity.isNullOrEmpty()) {
            firstView.visibility = View.VISIBLE
            secondView.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        } else {
            firstView.visibility = View.INVISIBLE
            secondView.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
            favoritesEntity.let { mAdapter?.setData(it) }
        }
    }

    override fun onItemClick(recipeId: Int) {
        viewModel.onRecipeDetailsAction(recipeId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.clearContextualActionMode()
    }
}