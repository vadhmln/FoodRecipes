package ru.vdh.foodrecipes.recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.recipes.domain.usecase.GetNewFeatureUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveNewFeatureUseCase
import ru.vdh.foodrecipes.recipes.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.foodrecipes.recipes.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.NewFeaturePresentationToDomainMapper
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeatureViewState
import javax.inject.Inject

@HiltViewModel
class RecipesFragmentViewModel @Inject constructor(
    private val getNewFeatureUseCase: GetNewFeatureUseCase,
    private val saveNewFeatureUseCase: SaveNewFeatureUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val newFeaturePresentationToDomainMapper: NewFeaturePresentationToDomainMapper,
    private val newFeatureDomainToPresentationMapper: NewFeatureDomainToPresentationMapper
) : BaseViewModel<NewFeatureViewState, NewFeaturePresentationNotification>(useCaseExecutorProvider) {

    override fun initialState() = NewFeatureViewState()

    private val resultMutableLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String> = resultMutableLiveData

    init {
        Log.e("AAA", "RecipesFragmentViewModel created!!!")
    }

    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "RecipesFragmentViewModel cleared!!!")
        super.onCleared()
    }

    fun onSecondFeatureAction(id: Int) {
        navigateTo(SecondFeature(id))
    }
}