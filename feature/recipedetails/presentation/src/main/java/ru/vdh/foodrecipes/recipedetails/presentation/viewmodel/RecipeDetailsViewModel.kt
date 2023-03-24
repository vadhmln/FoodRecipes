package ru.vdh.foodrecipes.recipedetails.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.recipedetails.domain.usecase.GetRecipeItemByIdUseCase
import ru.vdh.foodrecipes.recipedetails.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.RecipesDomainToPresentationMapper
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipedetails.presentation.model.ResultPresentationModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeItemByIdUseCase: GetRecipeItemByIdUseCase,
    private val recipesDomainToPresentationMapper: RecipesDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val state: SavedStateHandle
) : BaseViewModel<NewFeatureViewState, NewFeaturePresentationNotification>(useCaseExecutorProvider) {

    val liveData: LiveData<ResultPresentationModel?> = getItemById(state["recipeId"])

    private var _recipeItem = MutableLiveData<ResultPresentationModel?>()
    val recipeItem: LiveData<ResultPresentationModel?> = _recipeItem

    override fun initialState() = NewFeatureViewState.EXISTING_TODO

    init {
        Log.e("AAA", "UserDetailsViewModel created!!!")
        val name : String? = state["keyName"]
    }

    fun getItemById(recipeId: Int?) =
        getRecipeItemByIdUseCase.executeInBackground(recipeId)
            .map(recipesDomainToPresentationMapper::toPresentation).asLiveData()


    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun onSecondFeatureAction(id: Int) {
        navigateTo(SecondFeature(id))
    }
}