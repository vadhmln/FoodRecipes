package ru.vdh.foodrecipes.favoriterecipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.favoriterecipes.presentation.destination.SecondFeaturePresentationDestination.NewFeature
import ru.vdh.foodrecipes.favoriterecipes.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.foodrecipes.favoriterecipes.presentation.mapper.NewFeaturePresentationToDomainMapper
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.NewFeatureViewState
import javax.inject.Inject

@HiltViewModel
class SecondFeatureFragmentViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val newFeaturePresentationToDomainMapper: NewFeaturePresentationToDomainMapper,
    private val newFeatureDomainToPresentationMapper: NewFeatureDomainToPresentationMapper
) : BaseViewModel<NewFeatureViewState, NewFeaturePresentationNotification>(useCaseExecutorProvider) {

    override fun initialState() = NewFeatureViewState()

    private val resultMutableLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String> = resultMutableLiveData

    init {
        Log.e("AAA", "UserDetailsViewModel created!!!")
    }

    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun onNewFeatureAction(id: Int) {
        navigateTo(NewFeature(id))
    }

}