package ru.vdh.foodrecipes.favoriterecipes.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.favoriterecipes.presentation.viewmodel.SecondFeatureFragmentViewModel
import ru.vdh.foodrecipes.favoriterecipes.ui.R
import ru.vdh.foodrecipes.favoriterecipes.ui.databinding.FragmentFavoriteRecipesBinding
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.SecondFeatureDestinationToUiMapper
import ru.vdh.foodrecipes.favoriterecipes.ui.mapper.NewUserNotificationPresentationToUiMapper
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment<NewFeatureViewState, NewFeaturePresentationNotification>(),
    FavoriteRecipesViewsProvider {

    override val viewModel: SecondFeatureFragmentViewModel by viewModels()

    override val layoutResourceId = R.layout.fragment_favorite_recipes

    @Inject
    override lateinit var destinationMapper:
            SecondFeatureDestinationToUiMapper

    @Inject
    override lateinit var notificationMapper:
            NewUserNotificationPresentationToUiMapper

    @Inject
    @JvmSuppressWildcards
    override lateinit var viewStateBinder:
            ViewStateBinder<NewFeatureViewState, ViewsProvider>
    private var _binding: FragmentFavoriteRecipesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun View.bindViews() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonSecond.setOnClickListener {
            viewModel.onNewFeatureAction(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}