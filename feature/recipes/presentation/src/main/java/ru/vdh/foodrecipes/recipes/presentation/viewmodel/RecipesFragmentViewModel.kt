package ru.vdh.foodrecipes.recipes.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.foodrecipes.common.utils.Constants.Companion.API_KEY
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_DIET_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_MEAL_TYPE
import ru.vdh.foodrecipes.common.utils.Constants.Companion.DEFAULT_RECIPES_NUMBER
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_API_KEY
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_DIET
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_FILL_INGREDIENTS
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_NUMBER
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_SEARCH
import ru.vdh.foodrecipes.common.utils.Constants.Companion.QUERY_TYPE
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.recipes.domain.usecase.GetRecipesUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.ReadDatabaseUseCase
import ru.vdh.foodrecipes.recipes.domain.usecase.SaveMealAndDietTypeUseCase
import ru.vdh.foodrecipes.recipes.presentation.destination.NewFeaturePresentationDestination.RecipeDetails
import ru.vdh.foodrecipes.recipes.presentation.mapper.DataStoreDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesDatabaseDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.mapper.RecipesDomainToPresentationMapper
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationNotification
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesViewState
import ru.vdh.foodrecipes.recipes.presentation.model.RecipesPresentationModel
import javax.inject.Inject

@HiltViewModel
class RecipesFragmentViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val saveMealAndDietTypeUseCase: SaveMealAndDietTypeUseCase,
    private val readDatabaseUseCase: ReadDatabaseUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val dataStoreDomainToPresentationMapper: DataStoreDomainToPresentationMapper,
    private val recipesDomainToPresentationMapper: RecipesDomainToPresentationMapper,
    private val recipesDatabaseDomainToPresentationMapper: RecipesDatabaseDomainToPresentationMapper,
    private val application: Application,
) : BaseViewModel<RecipesViewState, RecipesPresentationNotification>(
    useCaseExecutorProvider
) {

    init {
        Log.e("AAA", "RecipesFragmentViewModel created!!!")
    }

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    override fun initialState() = RecipesViewState()

    val readRecipes: LiveData<RecipesPresentationModel> =
        readDatabaseUseCase.executeInBackground(0).map {
            recipesDatabaseDomainToPresentationMapper.toPresentation(it)
        }.asLiveData()

    var recipesResponse: LiveData<RecipesPresentationModel> = MutableLiveData()
    var searchedRecipesResponse: LiveData<RecipesPresentationModel> = MutableLiveData()

    lateinit var errorMassage: String
    var networkStatus = false
    var backOnline = false
    var isKeyLimited = false

    val readMealAndDietType =
        saveMealAndDietTypeUseCase.readMealAndDietType.map(dataStoreDomainToPresentationMapper::toPresentation)
    val readBackOnline = saveMealAndDietTypeUseCase.readBackOnline.asLiveData()

    fun onRecipeDetailsAction(recipeId: Int) {
        navigateTo(RecipeDetails(recipeId))
    }

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    fun searchRecipes(searchQuery: Map<String, String>) = viewModelScope.launch {
        searchRecipesSafeCall(searchQuery)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        try {
            if (hasInternetConnection()) {
                val list =
                    getRecipesUseCase.getRecipes(
                        queries,
                        onStart = { },
                        onComplete = { },
                        onError = {
                            it?.let {
                                when {
                                    it.contains("402") -> {
                                        errorMassage = "API Key Limited."
                                        isKeyLimited = true
                                    }

                                    it.contains("timeout") -> {
                                        errorMassage = "Timeout."
                                        isKeyLimited = false
                                    }
                                }
                            }

                            Log.e("getRecipesSafeCall", errorMassage)
                        }
                    ).asLiveData()
                recipesResponse = list.map(recipesDomainToPresentationMapper::toPresentation)

                updateViewState {
                    withRecipesList(list.map(recipesDomainToPresentationMapper::toPresentation))
                }
            } else {
                errorMassage = "No Internet Connection."
            }
        } catch (e: Exception) {
            errorMassage = "Recipes not found."
            e.printStackTrace()
        }
    }

    private suspend fun searchRecipesSafeCall(searchQuery: Map<String, String>) {
        try {
            if (hasInternetConnection()) {

                val list = getRecipesUseCase.searchRecipes(searchQuery).asLiveData()
                searchedRecipesResponse =
                    list.map(recipesDomainToPresentationMapper::toPresentation)

            } else {
                errorMassage = "No Internet Connection."
            }
        } catch (e: Exception) {
            errorMassage = "Recipes not found."
            Log.d("RecipesFragment", "RecipesFragmentViewModel $errorMassage")
            e.printStackTrace()
        }
    }

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            saveMealAndDietTypeUseCase.saveMealAndDietType(
                mealType,
                mealTypeId,
                dietType,
                dietTypeId
            )
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            saveMealAndDietTypeUseCase.saveBackOnline(backOnline)
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

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(application, "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(application, "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }

    override fun onCleared() {
        Log.e("AAA", "RecipesFragmentViewModel cleared!!!")
        super.onCleared()
    }
}