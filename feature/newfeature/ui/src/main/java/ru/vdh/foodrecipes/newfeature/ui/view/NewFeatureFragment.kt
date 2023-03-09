package ru.vdh.foodrecipes.newfeature.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipes.presentation.viewmodel.RecipesFragmentViewModel
import ru.vdh.foodrecipes.newfeature.ui.R
import ru.vdh.foodrecipes.newfeature.ui.mapper.NewFeatureDestinationToUiMapper
import ru.vdh.foodrecipes.newfeature.ui.mapper.NewUserNotificationPresentationToUiMapper
import javax.inject.Inject

private const val NO_LAYOUT_RESOURCE = 0

@AndroidEntryPoint
class NewFeatureFragment : BaseFragment<NewFeatureViewState, NewFeaturePresentationNotification>(),
    NewFeatureViewsProvider {

    override val viewModel: RecipesFragmentViewModel by viewModels()

    override val layoutResourceId = R.layout.new_feature_fragment

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
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        Log.e("AAA", "UserDetailsFragment created!!!")

        //подписка на изменение данных
        viewModel.resultLiveData.observe(viewLifecycleOwner) {

        }

        val view = if (layoutResourceId != NO_LAYOUT_RESOURCE) {
            inflater.inflate(layoutResourceId, container, false).apply {
                bindViews()
            }
        } else {
            null
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}