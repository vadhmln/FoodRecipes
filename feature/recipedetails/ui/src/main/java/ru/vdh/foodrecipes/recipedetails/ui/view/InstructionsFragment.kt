package ru.vdh.foodrecipes.recipedetails.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.recipedetails.presentation.viewmodel.RecipeDetailsViewModel
import ru.vdh.foodrecipes.recipedetails.ui.databinding.FragmentInstructionsBinding

@AndroidEntryPoint
class InstructionsFragment : Fragment() {

    private val viewModel: RecipeDetailsViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)

        viewModel.recipesLiveData.observe(viewLifecycleOwner) { recipeItem ->
            recipeItem?.let {
                binding.instructionsWebView.webViewClient = object : WebViewClient() {}
                val websiteUrl: String = recipeItem.sourceUrl
                binding.instructionsWebView.loadUrl(websiteUrl)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}