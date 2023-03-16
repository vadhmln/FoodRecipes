package ru.vdh.foodrecipes.recipes.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import ru.vdh.foodrecipes.common.utils.Constants.Companion.API_KEY
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_DIET_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_RECIPES_NUMBER
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_API_KEY
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_DIET
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_NUMBER
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_TYPE
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.recipes.domain.usecase.GetRecipesUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveNewFeatureUseCase
import ru.vdh.foodrecipes.recipes.presentation.NetworkResult
import ru.vdh.foodrecipes.recipes.presentation.NetworkResultUiState
import ru.vdh.foodrecipes.recipes.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.foodrecipes.recipes.presentation.mapper.ErrorResponseDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesPresentationToDomainMapper
import ru.vdh.foodrecipes.recipes.presentation.model.MealAndDietType
import ru.vdh.foodrecipes.recipes.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.recipes.presentation.model.RecipeErrorResponsePresentationModel
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesViewState
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RecipesFragmentViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val saveNewFeatureUseCase: SaveNewFeatureUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val recipesPresentationToDomainMapper: RecipesPresentationToDomainMapper,
    private val recipesDomainToPresentationMapper: RecipesDomainToPresentationMapper,
    private val errorResponseDomainToPresentationMapper: ErrorResponseDomainToPresentationMapper,
    private val application: Application,
) : BaseViewModel<RecipesViewState, NewFeaturePresentationNotification>(
    useCaseExecutorProvider
) {

    init {
        Log.e("AAA", "RecipesFragmentViewModel created!!!")
    }

    private lateinit var mealAndDiet: MealAndDietType

    override fun initialState() = RecipesViewState()

    var recipesResponse: LiveData<RecipesPresentationModel> = MutableLiveData()

    lateinit var errorMassage: String

    fun getRecipesSafeCall(queries: Map<String, String>) {
        try {
            if (hasInternetConnection()) {
                getRecipes(queries)
            } else {
                errorMassage = "No Internet Connection."
            }
        } catch (e: Exception) {
            errorMassage = "Recipes not found."
            e.printStackTrace()
        }

    }

    private fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        val list =
            getRecipesUseCase.getRecipes(
                queries,
                onStart = { },
                onComplete = { },
                onError = { }
            ).asLiveData()
        recipesResponse = list.map(recipesDomainToPresentationMapper::toPresentation)

        updateViewState {
            withRecipesList(list.map(recipesDomainToPresentationMapper::toPresentation))
        }
    }

    fun saveMealAndDietType() =
        viewModelScope.launch(Dispatchers.IO) {
            if (this@RecipesFragmentViewModel::mealAndDiet.isInitialized) {
//                dataStoreRepository.saveMealAndDietType(
//                    mealAndDiet.selectedMealType,
//                    mealAndDiet.selectedMealTypeId,
//                    mealAndDiet.selectedDietType,
//                    mealAndDiet.selectedDietTypeId
//                )
            }
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        if (this@RecipesFragmentViewModel::mealAndDiet.isInitialized) {
            queries[QUERY_TYPE] = mealAndDiet.selectedMealType
            queries[QUERY_DIET] = mealAndDiet.selectedDietType
        } else {
            queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
            queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        }

        return queries
    }

    private fun handleFoodRecipesResponse(response: Response<RecipesPresentationModel>): NetworkResultUiState<RecipesPresentationModel> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResultUiState.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResultUiState.Error("API Key Limited.")
            }

            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResultUiState.Error("Recipes not found.")
            }

            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResultUiState.Success(foodRecipes!!)
            }

            else -> {
                return NetworkResultUiState.Error(response.message())
            }
        }
    }

    fun hasInternetConnection(): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun offlineCacheRecipes(foodRecipe: RecipesPresentationModel) {
//        val recipesEntity = RecipesEntity(foodRecipe)
//        insertRecipes(recipesEntity)
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