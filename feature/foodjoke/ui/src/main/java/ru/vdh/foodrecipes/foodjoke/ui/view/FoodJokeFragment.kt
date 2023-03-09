package ru.vdh.foodrecipes.foodjoke.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vdh.foodrecipes.core.ui.mapper.ViewStateBinder
import ru.vdh.foodrecipes.core.ui.view.BaseFragment
import ru.vdh.foodrecipes.core.ui.view.ViewsProvider
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.foodjoke.presentation.viewmodel.FoodJokeFragmentViewModel
import ru.vdh.foodrecipes.foodjoke.ui.R
import ru.vdh.foodrecipes.foodjoke.ui.mapper.FoodJokeDestinationToUiMapper
import ru.vdh.foodrecipes.foodjoke.ui.mapper.FoodJokeNotificationPresentationToUiMapper
import javax.inject.Inject

private const val NO_LAYOUT_RESOURCE = 0

@AndroidEntryPoint
class FoodJokeFragment : BaseFragment<NewFeatureViewState, NewFeaturePresentationNotification>(),
    FoodJokeViewsProvider {

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