package ru.vdh.foodrecipes.favoriterecipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.favoriterecipes.domain.usecase.GetFavoriteRecipesUseCase
import ru.vdh.foodrecipes.favoriterecipes.presentation.destination.SecondFeaturePresentationDestination.NewFeature
import ru.vdh.foodrecipes.favoriterecipes.presentation.mapper.FavoriteRecipeDomainToPresentationMapper
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesPresentationNotification
import ru.vdh.foodrecipes.favoriterecipes.presentation.model.FavoriteRecipesViewState
import javax.inject.Inject

@HiltViewModel
class FavoriteRecipesFragmentViewModel @Inject constructor(
    private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val favoriteRecipeDomainToPresentationMapper: FavoriteRecipeDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : BaseViewModel<FavoriteRecipesViewState, FavoriteRecipesPresentationNotification>(useCaseExecutorProvider) {

    override fun initialState() = FavoriteRecipesViewState()

    val readFavoriteRecipes: LiveData<List<FavoritesPresentationModel>> =
        getFavoriteRecipesUseCase.getFavoriteRecipes().map { list ->
            list.map(favoriteRecipeDomainToPresentationMapper::toPresentation)
        }.asLiveData()

    init {
        Log.e("AAA", "UserDetailsViewModel created!!!")
    }

    fun deleteFavoriteRecipe(favoritesEntity: FavoritesPresentationModel) =
        viewModelScope.launch(Dispatchers.IO) {

        }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {

        }

    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun onNewFeatureAction(id: Int) {
        navigateTo(NewFeature(id))
    }

}