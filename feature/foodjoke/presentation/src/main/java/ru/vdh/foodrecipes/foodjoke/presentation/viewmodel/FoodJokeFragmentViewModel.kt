package ru.vdh.foodrecipes.foodjoke.presentation.viewmodel

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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vdh.foodrecipes.core.presentation.viewmodel.BaseViewModel
import ru.vdh.foodrecipes.core.presentation.viewmodel.usecase.UseCaseExecutorProvider
import ru.vdh.foodrecipes.foodjoke.domain.usecase.GetFoodJokeUseCase
import ru.vdh.foodrecipes.foodjoke.presentation.destination.NewFeaturePresentationDestination.SecondFeature
import ru.vdh.foodrecipes.foodjoke.presentation.mapper.FoodJokeDomainToPresentationMapper
import ru.vdh.foodrecipes.foodjoke.presentation.model.FoodJokePresentationModel
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeaturePresentationNotification
import ru.vdh.foodrecipes.foodjoke.presentation.model.NewFeatureViewState
import ru.vdh.foodrecipes.foodjoke.domain.usecase.GetFoodJokeFromRemoteDataSourceUseCase
import ru.vdh.foodrecipes.foodjoke.presentation.mapper.FoodJokeTextDomainToPresentationMapper
import ru.vdh.foodrecipes.foodjoke.presentation.model.FoodJokeTextPresentationModel
import javax.inject.Inject

@HiltViewModel
class FoodJokeFragmentViewModel @Inject constructor(
    private val getFoodJokeUseCase: GetFoodJokeUseCase,
    private val getFoodJokeFromRemoteDataSourceUseCase: GetFoodJokeFromRemoteDataSourceUseCase,
    private val foodJokeDomainToPresentationMapper: FoodJokeDomainToPresentationMapper,
    private val foodJokeTextDomainToPresentationMapper: FoodJokeTextDomainToPresentationMapper,
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val application: Application,
) : BaseViewModel<NewFeatureViewState, NewFeaturePresentationNotification>(useCaseExecutorProvider) {

    init {
        Log.e("AAA", "UserDetailsViewModel created!!!")
    }

    override fun initialState() = NewFeatureViewState()

    val readFoodJoke: LiveData<List<FoodJokePresentationModel>> =
        getFoodJokeUseCase.readFoodJoke().map { list ->
            list.map(foodJokeDomainToPresentationMapper::toPresentation)
        }.asLiveData()

    var foodJokeResponse: LiveData<FoodJokeTextPresentationModel> = MutableLiveData()

    lateinit var errorMassage: String

    fun getFoodJoke(apiKey: String) = viewModelScope.launch {
        getFoodJokeSafeCall(apiKey)
        Log.e("getFoodJoke", "foodJokeResponse called!!!")
    }

    private suspend fun getFoodJokeSafeCall(apiKey: String) {
        try {
            if (hasInternetConnection()) {
                val list =
                    getFoodJokeFromRemoteDataSourceUseCase.getFoodJoke(apiKey).asLiveData()
                foodJokeResponse = list.map(foodJokeTextDomainToPresentationMapper::toPresentation)

                Log.d("getFoodJokeSafeCall", "getFoodJokeSafeCall called")

            } else {
                errorMassage = "No Internet Connection."
            }
        } catch (e: Exception) {
            errorMassage = "FoodJoke not found."
            e.printStackTrace()
        }

    }

    //вызывается когда связанная с ней активити/fragment уничтожается
    override fun onCleared() {
        Log.e("AAA", "UserDetailsViewModel cleared!!!")
        super.onCleared()
    }

    fun onSecondFeatureAction(id: Int) {
        navigateTo(SecondFeature(id))
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
}