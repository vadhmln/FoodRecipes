package ru.vdh.foodrecipes.recipedetails.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.recipedetails.presentation.viewmodel.RecipeDetailsViewModel
import ru.vdh.foodrecipes.recipedetails.ui.adapter.IngredientsAdapter
import ru.vdh.foodrecipes.recipedetails.ui.databinding.FragmentIngredientsBinding

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private val viewModel: RecipeDetailsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private val adapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        setupRecyclerView()

        viewModel.liveData.observe(viewLifecycleOwner) { recipeItem ->
            recipeItem?.let {
                adapter.setData(recipeItem.extendedIngredients)
            }

        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerview.adapter = adapter
        binding.ingredientsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}