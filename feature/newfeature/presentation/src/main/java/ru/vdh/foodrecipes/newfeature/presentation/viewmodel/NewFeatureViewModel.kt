package ru.vdh.foodrecipes.newfeature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.newfeature.domain.usecase.GetNewFeatureUseCase
import ru.vdh.foodrecipes.newfeature.domain.usecase.SaveNewFeatureUseCase
import ru.vdh.foodrecipes.newfeature.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.foodrecipes.newfeature.presentation.mapper.NewFeatureDomainToPresentationMapper
import ru.vdh.foodrecipes.newfeature.presentation.mapper.NewFeaturePresentationToDomainMapper
import ru.vdh.foodrecipes.newfeature.presentation.model.NewFeaturePresentationModel
import ru.vdh.foodrecipes.newfeature.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.newfeature.presentation.model.NewFeatureViewState
import javax.inject.Inject

@HiltViewModel
class NewFeatureViewModel @Inject constructor(
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
        Log.e("AAA", "UserDetailsViewModel created!!!")
    }

    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun save(newFeaturePresentationModel: NewFeaturePresentationModel) {
        val domainNewUser =
            newFeaturePresentationToDomainMapper.toDomain(newFeaturePresentationModel)
        val result = execute(saveNewFeatureUseCase, domainNewUser)
        resultMutableLiveData.value = "Save result = $result"
    }

    fun onSecondFeatureAction(id: Int) {
        navigateTo(SecondFeature(id))
    }
}