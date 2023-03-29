package ru.vdh.foodrecipes.recipedetails.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.recipedetails.domain.usecase.DeleteFavoriteRecipeUseCase
import ru.vdh.foodrecipes.recipedetails.domain.usecase.GetFavoriteRecipesUseCase
import ru.vdh.foodrecipes.recipedetails.domain.usecase.GetRecipeItemByIdUseCase
import ru.vdh.foodrecipes.recipedetails.domain.usecase.InsertFavoriteRecipeUseCase
import ru.vdh.foodrecipes.recipedetails.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.FavoriteRecipeDomainToPresentationMapper
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.FavoriteRecipePresentationToDomainMapper
import ru.vdh.foodrecipes.recipedetails.presentation.mapper.RecipesDomainToPresentationMapper
import ru.vdh.foodrecipes.recipedetails.presentation.model.FavoritesPresentationModel
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipedetails.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.recipedetails.presentation.model.ResultPresentationModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeItemByIdUseCase: GetRecipeItemByIdUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
    private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase,
    private val recipesDomainToPresentationMapper: RecipesDomainToPresentationMapper,
    private val favoriteRecipePresentationToDomainMapper: FavoriteRecipePresentationToDomainMapper,
    private val favoriteRecipeDomainToPresentationMapper: FavoriteRecipeDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val state: SavedStateHandle
) : BaseViewModel<NewFeatureViewState, NewFeaturePresentationNotification>(useCaseExecutorProvider) {

    val recipesLiveData: LiveData<ResultPresentationModel?> = getItemById(state["recipeId"])
    val readFavoriteRecipes: LiveData<List<FavoritesPresentationModel>> =
        getFavoriteRecipesUseCase.getFavoriteRecipes().map { list ->
            list.map(favoriteRecipeDomainToPresentationMapper::toPresentation)
        }.asLiveData()

    private var _recipeItem = MutableLiveData<ResultPresentationModel?>()
    val recipeItem: LiveData<ResultPresentationModel?> = _recipeItem

    override fun initialState() = NewFeatureViewState.EXISTING_TODO

    init {
        Log.e("AAA", "UserDetailsViewModel created!!!")
        val name: String? = state["keyName"]
    }

    private fun getItemById(recipeId: Int?) =
        getRecipeItemByIdUseCase.executeInBackground(recipeId)
            .map(recipesDomainToPresentationMapper::toPresentation).asLiveData()

    fun insertFavoriteRecipe(favoritesEntity: FavoritesPresentationModel) =
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteRecipeUseCase.insert(
                favoriteRecipePresentationToDomainMapper.toDomain(favoritesEntity)
            )
            Log.d("RecipeDetailsViewModel", favoritesEntity.toString())
        }

    fun deleteFavoriteRecipe(favoritesEntity: FavoritesPresentationModel) =
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteRecipeUseCase.delete(
                favoriteRecipePresentationToDomainMapper.toDomain(favoritesEntity)
            )
        }

    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun onSecondFeatureAction(id: Int) {
        navigateTo(SecondFeature(id))
    }
}