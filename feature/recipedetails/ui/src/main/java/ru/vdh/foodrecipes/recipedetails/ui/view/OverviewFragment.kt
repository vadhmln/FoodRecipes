package ru.vdh.foodrecipes.recipedetails.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.common.utils.parseHtml
import ru.vdh.foodrecipes.recipedetails.presentation.viewmodel.RecipeDetailsViewModel
import ru.vdh.foodrecipes.recipedetails.ui.databinding.FragmentOverviewBinding

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipeDetailsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        viewModel.recipesLiveData.observe(viewLifecycleOwner) { recipeItem ->
            recipeItem?.let {
                binding.mainImageView.load(recipeItem.image)
                binding.titleTextView.text = recipeItem.title
                binding.likesTextView.text = recipeItem.aggregateLikes.toString()
                binding.timeTextView.text = recipeItem.readyInMinutes.toString()
                parseHtml(binding.summaryTextView, recipeItem.summary)
                updateColors(
                    recipeItem.vegetarian,
                    binding.vegetarianTextView,
                    binding.vegetarianImageView
                )
                updateColors(recipeItem.vegan, binding.veganTextView, binding.veganImageView)
                updateColors(recipeItem.cheap, binding.cheapTextView, binding.cheapImageView)
                updateColors(
                    recipeItem.dairyFree,
                    binding.dairyFreeTextView,
                    binding.dairyFreeImageView
                )
                updateColors(
                    recipeItem.glutenFree,
                    binding.glutenFreeTextView,
                    binding.glutenFreeImageView
                )
                updateColors(
                    recipeItem.veryHealthy,
                    binding.healthyTextView,
                    binding.healthyImageView
                )
            }
            Log.d("RecipeDetailsFragment", "recipeItem: $viewModel")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun updateColors(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
        if (stateIsOn) {
            imageView.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    ru.vdh.cleanarch.core.ui.R.color.green
                )
            )
            textView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    ru.vdh.cleanarch.core.ui.R.color.green
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}